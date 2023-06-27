package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.CommentDTO;
import raf.rs.rafnews_web_2023.model.entity.Comment;

public class CommentDTO_Converter {

    private CommentDTO_Converter(){

    }

    public CommentDTO convertToCommentDTO(Comment comment) {
        return new CommentDTO(comment);
    }

}
