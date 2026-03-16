import { useState } from "react";
import styles from "./CheckPrice.module.css";
import { fetchCheckPrice } from "./services/checkPriceApi";

export const CheckPrice = () => {
  const [inputValue, setInputValue] = useState("");
  const [data, setData] = useState(null);

  const getProductWithBarcode = async (e) => {
    const newValue = e.target.value.replace(/\D/g, "");
    setInputValue(newValue);

    if (newValue.length === 13) {
      const result = await fetchCheckPrice(newValue);
      setData(result);
      setInputValue("");
    }
  };

  const originalProduct = data?.originalProduct;
  const matchedProduct = data?.matchedProduct;
  const similarityScore = data?.similarityScore;

  const formatCurrency = (value) => {
    return new Intl.NumberFormat("pt-BR", {
      style: "currency",
      currency: "BRL",
    }).format(value || 0);
  };

  return (
    <div className={styles.container}>
      <input
        type="text"
        placeholder="Digite o código de barra do produto"
        value={inputValue}
        onChange={getProductWithBarcode}
        maxLength={13}
      />

      {originalProduct && (
        <div className={styles.content}>
          <aside className={styles.box}>
            <h2>Preços sugeridos</h2>
            {matchedProduct ? (
              <div className={styles.infoText}>
                <h4>{matchedProduct.description}</h4>
                <div className={styles.valuesGroup}>
                  <p>Preço de Venda: {formatCurrency(matchedProduct.price)}</p>
                  <p>
                    Preço de Compra: {formatCurrency(matchedProduct.costPrice)}
                  </p>
                  {similarityScore != null && (
                    <p>Similaridade: {similarityScore.toFixed(2)}%</p>
                  )}
                </div>
              </div>
            ) : (
              <p>Nenhuma sugestão encontrada.</p>
            )}
          </aside>

          <section className={styles.box}>
            <h2>Produto buscado:</h2>
            <div className={styles.infoText}>
              <h4>{originalProduct.description}</h4>
              <div className={styles.valuesGroup}>
                <p>Preço: {formatCurrency(originalProduct.price)}</p>
                <p>Quantidade: {originalProduct.quantity}</p>
              </div>
            </div>
          </section>
        </div>
      )}
    </div>
  );
};
