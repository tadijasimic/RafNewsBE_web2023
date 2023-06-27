package raf.rs.rafnews_web_2023.model.entity;


import raf.rs.rafnews_web_2023.model.dto.TagDTO;

public class Tag {

    private final int id;
    private final String name;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagDTO buildDTO() {
        return new TagDTO(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
