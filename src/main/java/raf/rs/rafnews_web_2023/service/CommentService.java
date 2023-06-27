package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.model.entity.Comment;
import raf.rs.rafnews_web_2023.model.dto.CommentDTO;
import raf.rs.rafnews_web_2023.repository.api.CommentRepositoryAPI;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentService {

    @Inject
    CommentRepositoryAPI commentRepository;

    public CommentService() {
    }

    public List<CommentDTO> allComments() {
        return buildListDTO(commentRepository.allComments());
    }

    public List<CommentDTO> commentsForPage(int pageIndex, int pageSize) {
        return buildListDTO(commentRepository.commentsForPage(pageIndex, pageSize));
    }

    public List<CommentDTO> commentsOnNews(int postId) {
        return buildListDTO(commentRepository.commentsOnNews(postId));
    }

    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = new Comment(
                commentDTO.getId(),
                commentDTO.getContent(),
                Timestamp.valueOf(commentDTO.getCreationTime()),
                commentDTO.getAuthorId(), commentDTO.getNewsId()
        );
        return commentRepository.addComment(comment).buildDTO();
    }

    public void deleteComment(CommentDTO commentDTO) {
        Comment comment = new Comment(
                commentDTO.getId(),
                commentDTO.getContent(),
                Timestamp.valueOf(commentDTO.getCreationTime()),
                commentDTO.getAuthorId(), commentDTO.getNewsId()
        );
        commentRepository.deleteComment(comment);
    }


    private List<CommentDTO> buildListDTO(List<Comment> comments) {
        List<CommentDTO> DTOs = new ArrayList<>();
        for (Comment comment : comments) {
            DTOs.add(comment.buildDTO());
        }
        return DTOs;
    }
}
