import styles from "./Main.module.css";
import { FunctionalityBox } from "./components/FunctionalityBox";

const Main = () => {
  return (
    <div className={styles.container}>
      <h1>Main</h1>

      <div className={styles.grid}>
        <FunctionalityBox title="feature1" description="description1" href="/feature1" />
        <FunctionalityBox title="feature2" description="description2" />
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
