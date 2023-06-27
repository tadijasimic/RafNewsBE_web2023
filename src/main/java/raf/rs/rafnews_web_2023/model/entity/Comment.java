package raf.rs.rafnews_web_2023.model.entity;

import raf.rs.rafnews_web_2023.dto.CommentDTO;

import java.sql.Timestamp;

public class Comment {
    private int id;

    private String content;

    private Timestamp creationTime;

    private int authorId;
    private int newsId;


    public Comment(int id, String content, Timestamp creationTime, int authorId, int postId){
        this.id = id;
        this.content = content;
        this.creationTime = creationTime;
        this.authorId = authorId;
        this.newsId = postId;
    }


    public CommentDTO buildDTO(){
        return new CommentDTO(this);
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

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

}
