import styles from "./CheckPrice.module.css";

export const CheckPrice = () => {
  return (
    <div className={styles.container}>
      <input type="text" placeholder="Digite o código de barra do produto" />

      <div className={styles.content}>
        <aside className={styles.box}>
          <h2>Preços sugeridos</h2>

          <div className={styles.suggestions}>
            <div className={styles.infoText}>
              <h4>Produto: </h4>

              <div className={styles.valuesGroup}>
                <p>Preço de Venda: </p>
                <p>Preço de Compra: </p>
              </div>
            </div>
          </div>
        </aside>

        <section className={styles.box}>
          <h2>Produto buscado:</h2>

          <div className={styles.infoText}>
            <h4>Produto:</h4>

            <div className={styles.valuesGroup}>
              <p>Preço: </p>
              <p>Quantidade: </p>
            </div>
          </div>
        </section>
      </div>
    </div>
  );
};
