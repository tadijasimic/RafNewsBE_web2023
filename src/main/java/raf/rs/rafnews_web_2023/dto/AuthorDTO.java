package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.User;

public class AuthorDTO {

    int id;
    private String name;
    private String surname;

    public AuthorDTO(User user) {
        name = user.getName();
        surname = user.getSurname();
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        id = userId;
    }
}
