package hr.fer.opp.mojkvart.rest.dto.out;

public class UserDTO {

    private Integer id;
    private String name;
    private String role;

    public UserDTO(Integer id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
