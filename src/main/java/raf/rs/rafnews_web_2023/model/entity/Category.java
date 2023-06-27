package raf.rs.rafnews_web_2023.model.entity;


import raf.rs.rafnews_web_2023.dto.CategoryDTO;

public class Category {

    private int id;
    private final String name;
    private final String description;

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO buildDTO(){
        return new CategoryDTO(this);
    }

    public void setId(int id) {
        this.id = id;
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
