import { Link } from 'react-router-dom';
import styles from './FunctionalityBox.module.css';

export const FunctionalityBox = ({ title, description, href }) => {
  return (
    <Link className={styles.box} to={href}>
      <h3>{title}</h3>
      <p>{description}</p>
    </Link>
  );
};