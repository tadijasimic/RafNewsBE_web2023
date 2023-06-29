package raf.rs.rafnews_web_2023.model;


import raf.rs.rafnews_web_2023.dto.TagDTO;

public class Tag {

    private final int id;
    private final String name;

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
