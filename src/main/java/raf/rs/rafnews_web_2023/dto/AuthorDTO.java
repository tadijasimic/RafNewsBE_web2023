package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.entity.User;

public class AuthorDTO {

    private String author;

    public AuthorDTO(User user) {
        author = user.getName() + " " + user.getSurname();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
