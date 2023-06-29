package raf.rs.rafnews_web_2023.model;

import raf.rs.rafnews_web_2023.dto.NewsDTO;

import java.sql.Timestamp;

public class News {

    private final String title;
    private final String content;
    private final Timestamp creationTime;
    private final int authorId;
    private final int categoryId;
    private int id;
    private int visited;

    public News(int id, String title, String content, int visited, String creationTime, int authorId, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.visited = visited;
        this.creationTime = Timestamp.valueOf(creationTime);
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    public News(int id, String title, String content, int visited, Timestamp creationTime, int authorId, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.visited = visited;
        this.creationTime = creationTime;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public String getContent() {
        return content;
    }


    public Timestamp getCreationTime() {
        return creationTime;
    }


    public int getAuthorId() {
        return authorId;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public int getVisited() {
        return visited;
    }
}
