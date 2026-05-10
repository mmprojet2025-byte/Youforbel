package be.iccbxl.pid.youforbel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ArtistRequestDTO {

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 60, message = "Le prénom doit contenir entre 2 et 60 caractères")
    private String firstname;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 60, message = "Le nom doit contenir entre 2 et 60 caractères")
    private String lastname;

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
}