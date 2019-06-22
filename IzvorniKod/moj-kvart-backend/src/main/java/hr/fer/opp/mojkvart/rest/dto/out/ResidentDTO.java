package hr.fer.opp.mojkvart.rest.dto.out;

import java.time.LocalDateTime;

public class ResidentDTO {

    private Integer id;
    private String username;
    private LocalDateTime createdOn;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String role;

    public ResidentDTO(Integer id, String username, LocalDateTime createdOn, String firstName, String lastName, String email, String street, String role) {
        this.id = id;
        this.username = username;
        this.createdOn = createdOn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.street = street;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getStreet() {
        return street;
    }

    public String getRole() {
        return role;
    }
}
