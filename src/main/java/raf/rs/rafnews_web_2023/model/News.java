package raf.rs.rafnews_web_2023.model;

import raf.rs.rafnews_web_2023.util.Util;

import java.sql.Timestamp;

public class News {

    private  String title;
    private  String content;
    private  Timestamp creationTime;
    private  int authorId;
    private  int categoryId;
    private int id;
    private int visited;
    public News(){}
    public News(int id, String title, String content, int visited, String creationTime, int authorId, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.visited = visited;
        this.creationTime = Util.parseDateString(creationTime);
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

    public void setVisited(int visited) {
        this.visited = visited;
    }
}
