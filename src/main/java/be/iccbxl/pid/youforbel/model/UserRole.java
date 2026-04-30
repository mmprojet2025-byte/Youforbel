package be.iccbxl.pid.youforbel.model;

public enum UserRole {
    ADMIN("Administrateur"),
    MEMBER("Membre");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getValue() {
        return role;
    }
}
