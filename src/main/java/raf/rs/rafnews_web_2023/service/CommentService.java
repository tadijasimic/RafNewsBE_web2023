package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.converter.CommentDTO_Converter;
import raf.rs.rafnews_web_2023.dto.user.AuthorDTO;
import raf.rs.rafnews_web_2023.dto.comment.CommentDTO;
import raf.rs.rafnews_web_2023.model.Comment;
import raf.rs.rafnews_web_2023.repository.api.CommentRepositoryAPI;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    CommentRepositoryAPI commentRepository;

    @Inject
    private UserService userService;


    public CommentService() {
    }

    public List<CommentDTO> allComments() {

        List<Comment> comments = commentRepository.allComments();
        List<AuthorDTO> author = userService.searchCommentsAuthors(comments);
        return CommentDTO_Converter.convertToCommentDTOList(comments, author);

    }

    public List<CommentDTO> commentsOnNews(int newsId, int pageIndex, int pageSize) {
        List<Comment> comments = commentRepository.commentsOnNews(newsId, pageIndex, pageSize);
        List<AuthorDTO> authors = userService.searchCommentsAuthors(comments);
        return CommentDTO_Converter.convertToCommentDTOList(comments, authors);
    }

    public List<CommentDTO> commentsOnNews(int newsId) {
        List<Comment> comments = commentRepository.commentsOnNews(newsId);
        List<AuthorDTO> authors = userService.searchCommentsAuthors(comments);
        return CommentDTO_Converter.convertToCommentDTOList(comments, authors);
    }

    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = CommentDTO_Converter.convertToComment(commentDTO);
        comment = commentRepository.addComment(comment);
        return CommentDTO_Converter.convertToCommentDTO(
                comment,
                userService.searchAuthor(comment)
        );
    }

    public void deleteComment(CommentDTO commentDTO) {
        commentRepository.deleteComment(
                CommentDTO_Converter.convertToComment(commentDTO)
        );
    }


}
