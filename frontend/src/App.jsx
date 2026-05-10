import { BrowserRouter, Routes, Route, Link } from "react-router-dom";

import HomePage from "./pages/HomePage";
import LoginPage from "./pages/LoginPage";
import ArtistsPage from "./pages/ArtistsPage";

export default function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">Accueil</Link> |{" "}
        <Link to="/login">Connexion</Link> |{" "}
        <Link to="/artists">Artistes</Link>
      </nav>

      <hr />

      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/artists" element={<ArtistsPage />} />
      </Routes>
    </BrowserRouter>
  );
}