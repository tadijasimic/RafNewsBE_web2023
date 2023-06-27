package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.entity.Category;

public class CategoryDTO {

    private int id;

    private String name;

    private String description;

    public CategoryDTO() {

    }

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
        description = category.getDescription();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
