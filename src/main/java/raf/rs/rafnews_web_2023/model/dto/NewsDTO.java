package raf.rs.rafnews_web_2023.model.dto;

import raf.rs.rafnews_web_2023.model.entity.News;

public class NewsDTO {

    private int id;
    private String title;

    private String content;

    private  String creationTime;

    private int authorId;

    private int categoryId;

    public NewsDTO(){
    }

    public NewsDTO(News news){
        id = news.getId();
        title = news.getTitle();
        content = news.getContent();
        creationTime = news.getCreationTime().toString();
        authorId = news.getAuthorId();
        categoryId = news.getCategoryId();
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

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public int getAuthorId() {
        return authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
