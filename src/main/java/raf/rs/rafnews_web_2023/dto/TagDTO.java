package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.Tag;

public class TagDTO {

    private int id;
    private String name;


    public TagDTO(){

    }

    public TagDTO(Tag tag){
        id = tag.getId();
        name = tag.getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
