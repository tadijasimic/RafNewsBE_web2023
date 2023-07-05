package raf.rs.rafnews_web_2023.dto.user;

import raf.rs.rafnews_web_2023.model.User;

public class UserDTO {

    private int id;
    private String email;
    private String firstName;

    private String lastName;

    private String role;
    private String status;

    private String password;


    public UserDTO() {
    }

    public UserDTO(User user) {

        id = user.getId();
        email = user.getEmail();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        role = user.getRole().name();
        status = user.getStatus().name();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surname) {
        this.lastName = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
