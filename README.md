<div align="center">

<br/>

```
███╗   ██╗███████╗██╗  ██╗██╗   ██╗███████╗
████╗  ██║██╔════╝╚██╗██╔╝██║   ██║██╔════╝
██╔██╗ ██║█████╗   ╚███╔╝ ██║   ██║███████╗
██║╚██╗██║██╔══╝   ██╔██╗ ██║   ██║╚════██║
██║ ╚████║███████╗██╔╝ ██╗╚██████╔╝███████║
╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝
```

**Central orchestration layer for ERP automation and intelligent product integration**

<br/>

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Python](https://img.shields.io/badge/Python_3.13-3776AB?style=for-the-badge&logo=python&logoColor=white)
![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=for-the-badge&logo=fastapi&logoColor=white)
![React](https://img.shields.io/badge/React_19-61DAFB?style=for-the-badge&logo=react&logoColor=black)

<br/>

</div>

---

## O Problema Real

> *"Temos dois catálogos de produtos com nomes diferentes. Precisamos saber se o preço que praticamos hoje ainda está correto comparado ao histórico."*

Essa foi a demanda de um usuário real. Simples de enunciar, complexa de resolver.

O GDoor Web — ERP utilizado pelo cliente — não oferece uma API de autenticação pública. Os produtos cadastrados no sistema novo têm nomes ligeiramente distintos dos registros antigos. Cruzar manualmente centenas de itens para auditar preços era inviável.

**Nexus foi construído para resolver exatamente isso.**

---

## A Solução

Nexus é uma plataforma de integração composta por três serviços independentes que trabalham em conjunto para automatizar a comparação inteligente de preços entre catálogos de produtos.

O fluxo completo acontece com um simples scan de código de barras:

```
Operador escaneia      Backend consulta       Motor Python
o código de barras  →  o produto no ERP   →  compara com a
no frontend            via API autenticada    base histórica
                                                    ↓
                       Resultado exibido  ←  Retorna o match
                       com preço sugerido     mais similar e
                       e score de            o score de
                       similaridade          confiança
```

---

## Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                        NEXUS SYSTEM                         │
│                                                             │
│  ┌──────────────┐    ┌──────────────┐    ┌───────────────┐  │
│  │   Frontend   │    │   Backend    │    │  Automation   │  │
│  │              │    │              │    │    Engine     │  │
│  │  React 19    │───▶│ Spring Boot  │───▶│               │  │
│  │  Vite 7      │    │     4.0      │    │  FastAPI      │  │
│  │  CSS Modules │    │  Java 17     │    │  RapidFuzz    │  │
│  │              │    │              │    │  Playwright   │  │
│  └──────────────┘    └──────┬───────┘    └───────────────┘  │
│                             │                               │
│                             ▼                               │
│                    ┌─────────────────┐                      │
│                    │  GDoor Web API  │                      │
│                    │  (ERP externo)  │                      │
│                    └─────────────────┘                      │
└─────────────────────────────────────────────────────────────┘
```

Cada serviço tem responsabilidade única e se comunica via HTTP com contratos bem definidos.

---

## Decisões Técnicas que Importam

### 🔐 Autenticação sem API pública — Playwright como solução criativa

O GDoor Web não disponibiliza um endpoint de autenticação convencional. A solução foi usar **Playwright** para controlar um browser Chromium em modo headless, executar o fluxo de login programaticamente e capturar o JWT diretamente do `localStorage` — exatamente como o browser do usuário faria.

```python
# O browser abre, faz login e extrai o token silenciosamente
token = await page.evaluate(
    f"() => localStorage.getItem('{NAME_LOCAL_STORAGE_TOKEN}')"
)
```

### ♻️ Token Management automático no Backend

O backend gerencia o ciclo de vida do JWT de forma transparente: busca o token na inicialização e o renova automaticamente a cada 2 horas via `@Scheduled`, sem intervenção manual e sem impacto nas requisições em andamento graças ao uso de `AtomicReference`.

```java
@EventListener(ApplicationReadyEvent.class)
public void fetchTokenOnStartup() { this.updateToken(); }

@Scheduled(fixedRate = 7200000) // 2 horas
public void fetchTokenScheduled() { this.updateToken(); }
```

### 🧠 Fuzzy Matching com RapidFuzz

Produtos com nomes como `"Café Torrado Extra Forte 500g"` e `"Café Extra Forte Torrado 500gr"` devem ser reconhecidos como o mesmo item. O algoritmo `token_sort_ratio` resolve isso normalizando a ordem dos tokens antes de comparar — tolerando variações de nomenclatura reais de catálogos comerciais.

```python
match = process.extractOne(
    query=product.description,
    choices=PRODUCTS_NAME,
    scorer=fuzz.token_sort_ratio  # tolerante à ordem das palavras
)
```

### 🗺️ Separação entre Domínio e Infraestrutura

O backend segue arquitetura em camadas com separação clara: o domínio (`Product`, `ProductMatchResult`) não conhece o GDoor nem o serviço de automação. Os mappers (MapStruct) fazem a tradução entre DTOs externos e o modelo interno, mantendo o núcleo do sistema independente de qualquer fornecedor externo.

---

## Stack Tecnológica

| Camada | Tecnologia | Versão |
|---|---|---|
| Frontend | React + Vite | 19 / 7 |
| Backend | Spring Boot + Java | 4.0 / 17 |
| Automation Engine | FastAPI + Uvicorn | latest |
| Fuzzy Matching | RapidFuzz | latest |
| Browser Automation | Playwright (Chromium) | latest |
| Object Mapping | MapStruct | 1.5.5 |
| HTTP Client | Spring RestClient | — |
| Validação de Schema | Pydantic v2 | — |

---

## Estrutura do Projeto

```
Nexus/
├── frontend/                  # Interface React
│   └── src/
│       └── features/
│           ├── main/          # Tela inicial com navegação
│           └── check-price/   # Funcionalidade principal
│
├── backend/                   # Orquestrador Spring Boot
│   └── src/main/java/
│       ├── controller/        # Endpoints REST
│       ├── application/       # Lógica de negócio (Services)
│       ├── domain/            # Entidades e value objects
│       └── infrastructure/    # Clients HTTP e configuração
│           ├── client/gdoor/      # Integração GDoor Web
│           └── client/automation/ # Integração com Python Engine
│
└── automation/                # Motor de processamento Python
    └── app/
        ├── api/routes/        # Endpoints FastAPI
        ├── core/use_cases/    # Fuzzy matcher + autenticação
        └── schemas/           # Contratos Pydantic
```

---

## Como Executar

### Pré-requisitos

- Java 17+
- Python 3.13+
- Node.js 18+
- Brave Browser (ou Chromium) instalado

### Automation Engine

```bash
cd automation
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8000
```

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

> Configure as credenciais do GDoor em `automation/app/core/use_cases/gdoor_authentication/authentication_settings.py` antes de iniciar.

---

## Sobre o Desenvolvedor

**Arthur Rocha** é um desenvolvedor focado em construir soluções que resolvem problemas reais — não apenas exercícios técnicos.

Nexus demonstra capacidade de navegar com segurança por múltiplos ecossistemas (JVM, Python, JavaScript), projetar arquiteturas com separação de responsabilidades clara, e encontrar soluções criativas onde as APIs convencionais não chegam.

<div align="center">

<br/>

[![GitHub](https://img.shields.io/badge/GitHub-arthurRocha01-181717?style=for-the-badge&logo=github)](https://github.com/arthurRocha01)

<br/>

</div>

---

<div align="center">
<sub>Construído para resolver um problema real. Projetado para durar.</sub>
</div>
