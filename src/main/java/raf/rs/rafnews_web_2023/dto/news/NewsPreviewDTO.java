package raf.rs.rafnews_web_2023.dto.news;

import raf.rs.rafnews_web_2023.dto.category.CategoryDTO;
import raf.rs.rafnews_web_2023.dto.user.AuthorDTO;
import raf.rs.rafnews_web_2023.model.News;

import java.time.format.DateTimeFormatter;

public class NewsPreviewDTO {

    private int id;
    private String title;
    private String content;

    private String creationTime;

    private int visited;

    private AuthorDTO author;

    private CategoryDTO category;


    public NewsPreviewDTO() {

    }

    public NewsPreviewDTO(News news, AuthorDTO authorDTO, CategoryDTO categoryDTO) {
        id = news.getId();
        title = news.getTitle();
        content = news.getContent();
        visited = news.getVisited();
        creationTime = news.getCreationTime().toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        author = authorDTO;
        category = categoryDTO;
    }

    public int getId() {
        return id;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }
}




