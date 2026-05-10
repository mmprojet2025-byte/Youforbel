import { useState } from "react";
import API_URL from "../api.js";

export default function LoginPage() {

  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (event) => {

    event.preventDefault();

    try {

      const response = await fetch(
        `${API_URL}/auth/login`,
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json",
          },

          body: JSON.stringify({
            login,
            password,
          }),
        }
      );

      const data = await response.json();

      console.log(data);

      // Sauvegarder token JWT
      localStorage.setItem("token", data.token);

      alert("Connexion réussie");

    } catch (error) {

      console.error(error);

      alert("Erreur de connexion");
    }
  };

  return (
    <div>

      <h1>Connexion</h1>

      <form onSubmit={handleSubmit}>

        <div>
          <label>Login</label>
          <br />

          <input
            type="text"
            value={login}
            onChange={(e) => setLogin(e.target.value)}
          />
        </div>

        <br />

        <div>
          <label>Mot de passe</label>
          <br />

          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <br />

        <button type="submit">
          Se connecter
        </button>

      </form>

    </div>
  );
}