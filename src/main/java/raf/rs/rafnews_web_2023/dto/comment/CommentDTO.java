package raf.rs.rafnews_web_2023.dto.comment;

import raf.rs.rafnews_web_2023.dto.user.AuthorDTO;
import raf.rs.rafnews_web_2023.model.Comment;

public class CommentDTO {

    private int id;
    private String content;

    private String creationTime;

    private AuthorDTO author;

    private int newsId;


    public CommentDTO() {

    }

    public CommentDTO(Comment comment, AuthorDTO author) {
        id = comment.getId();
        content = comment.getContent();
        creationTime = comment.getCreationTime().toString();
        this.author = author;
        this.newsId = comment.getNewsId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }
}
