package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.entity.Comment;

public class CommentDTO {

    private int id;
    private String content;

    private String creationTime;

    private int authorId;

    private int newsId;

    public  CommentDTO(){

    }

    public CommentDTO(Comment comment) {
        id = comment.getId();
        content = comment.getContent();
        creationTime = comment.getCreationTime().toString();
        authorId = comment.getAuthorId();
        newsId = comment.getNewsId();
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

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
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

    public int getAuthorId() {
        return authorId;
    }

    public int getNewsId() {
        return newsId;
    }
}
