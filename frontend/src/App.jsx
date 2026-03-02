import { Routes, Route } from "react-router-dom";
import "./App.css";
import Main from "./features/main/Main";
import { CheckPrice } from "./features/check-price/CheckPrice";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/check-prices" element={<CheckPrice />} />
    </Routes>
  );
}

export default App;
