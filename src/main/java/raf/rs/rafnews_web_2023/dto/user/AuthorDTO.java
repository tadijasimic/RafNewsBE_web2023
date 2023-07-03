package raf.rs.rafnews_web_2023.dto.user;

import raf.rs.rafnews_web_2023.model.User;

public class AuthorDTO {

    int id;
    private String firstName;
    private String lastName;

    public AuthorDTO(User user) {
        firstName = user.getName();
        lastName = user.getSurname();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        id = userId;
    }
}
