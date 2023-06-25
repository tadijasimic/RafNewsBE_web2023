package raf.rs.rafnews_web_2023.entity;

public class News {

    private int id;
    private String title;
    private String content;
    private int authorId;

    private int categoryId;

    public News(){};
    public News(int id, String title, String content, int authorId, int categoryId) {
        this.id = id;
        this.title = title;
        this.content = content;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
