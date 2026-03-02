import styles from "./Main.module.css";
import { FunctionalityBox } from "./components/FunctionalityBox";

const Main = () => {
  return (
    <div className={styles.container}>
      <h1>Main</h1>

      <div className={styles.grid}>
        <FunctionalityBox title="Atualizar Preços" description="Atualizar preços de produtos com valor zerado." href="/update-prices" />
        <FunctionalityBox title="Consultar Preço" description="Consular preço de custo e de venda do produto." href="/check-prices" />
        <FunctionalityBox title="feature3" description="description3" />
        <FunctionalityBox title="feature4" description="description4" />
        <FunctionalityBox title="feature5" description="description5" />
        <FunctionalityBox title="feature6" description="description6" />
        <FunctionalityBox title="feature7" description="description7" />
        <FunctionalityBox title="feature8" description="description8" />
      </div>
    </div>
  );
};

export default Main;
