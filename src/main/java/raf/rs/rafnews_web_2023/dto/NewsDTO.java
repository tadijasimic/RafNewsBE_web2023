package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.News;

import java.util.List;

public class NewsDTO {

    private int id;
    private String title;

    private String content;

    private  String creationTime;

    private AuthorDTO author;

    private List<CommentDTO> comments;



    public NewsDTO(){
    }

    public NewsDTO(News news, AuthorDTO author, List<CommentDTO> comments){
        id = news.getId();
        title = news.getTitle();
        content = news.getContent();
        creationTime = news.getCreationTime().toString();
        this.author = author;
        this.comments = comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public List<CommentDTO> getComments() {
        return comments;
    }
}
