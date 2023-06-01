package entity;

import lombok.Data;

@Data
public class Partner {
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String password;
    private String confirmPassword;
    private String organizationName;
    private String organizationPosition;

    public Partner(String email, String firstName, String lastName, Gender gender, String password,
                   String confirmPassword, String organizationName, String organizationPosition) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.organizationName = organizationName;
        this.organizationPosition = organizationPosition;
    }
}
