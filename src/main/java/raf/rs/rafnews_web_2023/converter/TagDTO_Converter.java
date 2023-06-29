package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.TagDTO;
import raf.rs.rafnews_web_2023.model.Tag;

import java.util.ArrayList;
import java.util.List;

public abstract class TagDTO_Converter {

    private TagDTO_Converter() {

    }

    public static TagDTO convertToTagDTO(Tag tag) {
        return new TagDTO(tag);
    }

    public static List<TagDTO> convertToTagDTOList(List<Tag> tags) {
        List<TagDTO> dtoList = new ArrayList<>();
        for (Tag tag : tags) {
            dtoList.add(convertToTagDTO(tag));
        }
        return dtoList;
    }

    public static Tag convertToTag(TagDTO dto) {
        return new Tag(dto.getId(), dto.getName());
    }


}
