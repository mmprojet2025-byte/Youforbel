package be.iccbxl.pid.youforbel.dto;

public class ArtistDTO {

    private Long id;
    private String firstname;
    private String lastname;

    public ArtistDTO() {
    }

    public ArtistDTO(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}

