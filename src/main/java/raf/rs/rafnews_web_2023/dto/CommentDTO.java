package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.Comment;

public class CommentDTO {

    private int id;
    private String content;

    private String creationTime;

    private AuthorDTO author;


    public  CommentDTO(){

    }

    public CommentDTO(Comment comment, AuthorDTO author) {
        id = comment.getId();
        content = comment.getContent();
        creationTime = comment.getCreationTime().toString();
        this.author = author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }



    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

}
