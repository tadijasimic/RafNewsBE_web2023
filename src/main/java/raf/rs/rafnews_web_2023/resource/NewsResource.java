package raf.rs.rafnews_web_2023.resource;


import raf.rs.rafnews_web_2023.dto.filter.FilterDTO;
import raf.rs.rafnews_web_2023.dto.news.NewsDTO;
import raf.rs.rafnews_web_2023.dto.news.NewsPreviewDTO;
import raf.rs.rafnews_web_2023.model.User;
import raf.rs.rafnews_web_2023.service.NewsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    private NewsService newsService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsPreviewDTO> allNews() {
        return newsService.allNews();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsPreviewDTO> newsForPage(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize) {
        return newsService.newsForPage(pageIndex, pageSize);
    }

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsPreviewDTO> filterNews(FilterDTO filterDTO, @QueryParam("pageSize") int pageSize, @QueryParam("pageIndex") int pageIndex) {
        return null;
    }

    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsPreviewDTO> newsInCategory(@PathParam("categoryId") int categoryId) {
        return newsService.newsInCategory(categoryId);
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsDTO newsById(@PathParam("id") int newsId) {
        return newsService.findById(newsId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public NewsDTO addNews(NewsPreviewDTO news) {
        return newsService.addNews(news);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public NewsPreviewDTO editNews(NewsPreviewDTO news) {
        return newsService.editNews(news);
    }

    @DELETE
    @Path("/{id}")
    public void deleteNews(@PathParam("id") int id) {
        newsService.deleteNews(id);
    }
}
