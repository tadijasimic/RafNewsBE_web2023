package raf.rs.rafnews_web_2023.dto.user;

import raf.rs.rafnews_web_2023.model.User;

public class UserDTO {

    private int id;
    private String email;
    private String fistName;

    private String lastName;

    private String role;
    private String status;


    public UserDTO() {
    }

    public UserDTO(User user) {

        id = user.getId();
        email = user.getEmail();
        fistName = user.getName();
        lastName = user.getSurname();
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

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getSurname() {
        return lastName;
    }

    public void setSurname(String surname) {
        this.lastName = surname;
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
