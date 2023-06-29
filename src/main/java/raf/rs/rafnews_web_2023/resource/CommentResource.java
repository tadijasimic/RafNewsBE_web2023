package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.dto.CommentDTO;
import raf.rs.rafnews_web_2023.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {
    @Inject
    private CommentService commentService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommentDTO> allComments() {
        return commentService.allComments();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommentDTO> usersForPage(@QueryParam("newsId") int newsId, @QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        return commentService.commentsOnNews(newsId, pageIndex, pageSize);
    }

    @GET
    @Path("/onNews/{newsId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CommentDTO> commentsOnNews(@PathParam("newsId") int newsId) {
        return commentService.commentsOnNews(newsId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public CommentDTO addComment(CommentDTO comment) {
        return commentService.addComment(comment);
    }


}
