package be.iccbxl.pid.youforbel.model;

// Import des annotations JPA (relation Java ↔ base de données)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity = cette classe correspond à une table en base de données
@Entity

// @Table = nom exact de la table dans MySQL
@Table(name = "artists")
public class Artist {

    // @Id = clé primaire de la table
    @Id

    // @GeneratedValue = l'id est généré automatiquement (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Colonne firstname dans la table artists
    private String firstname;

    // Colonne lastname dans la table artists
    private String lastname;

    // Constructeur vide obligatoire pour JPA
    protected Artist() {}

    // Constructeur pratique pour créer un artiste en Java
    public Artist(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Getters / Setters : permettent à Spring/JPA d'accéder aux données

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Méthode appelée quand on affiche l'objet (ex: dans un log ou une liste)
    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
