package raf.rs.rafnews_web_2023.entity.dto;

import raf.rs.rafnews_web_2023.entity.User;

public class UserDTO {

    private int id;
    private String email;
    private String name;

    private String surname;

    private String role;
    private String status;


    public  UserDTO(){

    }

    public UserDTO(User user) {
        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        surname = user.getSurname();
        role = user.getRole().name();
        status = user.getStatus().name();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}
