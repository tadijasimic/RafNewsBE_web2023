package raf.rs.rafnews_web_2023.model.dto;

import raf.rs.rafnews_web_2023.model.entity.Tag;

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

}
