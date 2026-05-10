import { useEffect, useState } from "react";
import API_URL from "../api.js";

export default function ArtistsPage() {

  const [artists, setArtists] = useState([]);

  useEffect(() => {

    const token = localStorage.getItem("token");

    fetch(`${API_URL}/artists`, {

      headers: {
        Authorization: `Bearer ${token}`,
      },

    })
      .then((response) => response.json())
      .then((data) => {

        console.log(data);

        setArtists(data);
      })
      .catch((error) => {
        console.error("Erreur API :", error);
      });

  }, []);

  return (
    <div>

      <h1>Artistes</h1>

      <ul>

        {artists.map((artist) => (

          <li key={artist.id}>

            {artist.firstname} {artist.lastname}

          </li>
        ))}

      </ul>

    </div>
  );
}