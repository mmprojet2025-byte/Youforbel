package be.iccbxl.pid.youforbel.model;

// Import des annotations JPA (relation Java ↔ base de données)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Import des validations
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// @Entity = cette classe correspond à une table en base de données
@Entity

// @Table = nom exact de la table dans MySQL
@Table(name = "artists")
public class Artist {

    // ===============================
    // ID
    // ===============================

    // @Id = clé primaire de la table
    @Id

    // @GeneratedValue = l'id est généré automatiquement (AUTO_INCREMENT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===============================
    // FIRSTNAME
    // ===============================

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 50, message = "Le prénom doit contenir entre 2 et 50 caractères")
    private String firstname;

    // ===============================
    // LASTNAME
    // ===============================

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 50, message = "Le nom doit contenir entre 2 et 50 caractères")
    private String lastname;

    // ===============================
    // CONSTRUCTEURS
    // ===============================

    // Constructeur vide obligatoire pour JPA
    public Artist() {}

    // Constructeur pratique pour créer un artiste en Java
    public Artist(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // ===============================
    // GETTERS / SETTERS
    // ===============================

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

    // ===============================
    // TOSTRING
    // ===============================

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}