package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.TagDTO;
import raf.rs.rafnews_web_2023.model.entity.Tag;

public abstract class  TagDTO_Converter {

    private TagDTO_Converter(){

    }

    public TagDTO convertToTagDTO(Tag tag) {
        return new TagDTO(tag);
    }

}
