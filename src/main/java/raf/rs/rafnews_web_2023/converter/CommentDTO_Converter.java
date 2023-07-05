package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.user.AuthorDTO;
import raf.rs.rafnews_web_2023.dto.comment.CommentDTO;
import raf.rs.rafnews_web_2023.model.Comment;

import java.util.ArrayList;
import java.util.List;

public abstract class CommentDTO_Converter {

    private CommentDTO_Converter() {

    }

    public static CommentDTO convertToCommentDTO(Comment comment, AuthorDTO author) {
        if(comment == null || author == null)
            return null;
        return new CommentDTO(comment, author);
    }

    public static List<CommentDTO> convertToCommentDTOList(List<Comment> comments, List<AuthorDTO> authors) {
        if(comments == null || authors== null)
            return null;
        List<CommentDTO> dtoList = new ArrayList<>();
        if (comments.size() != authors.size())
            return null;
        for (int i = 0; i < comments.size(); i++) {
            dtoList.add(
                    convertToCommentDTO(
                            comments.get(i),
                            authors.get(i)
                    )
            );
            System.out.println("KITOTIJE");

        }
        return dtoList;
    }

    public static Comment convertToComment(CommentDTO dto) {
        if(dto == null)
            return null;
        return new Comment(
                dto.getId(),
                dto.getContent(),
                dto.getCreationTime(),
                dto.getAuthor().getId(),
                dto.getNewsId()
        );
    }

}
