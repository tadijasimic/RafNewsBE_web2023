package raf.rs.rafnews_web_2023.resource;


import raf.rs.rafnews_web_2023.dto.news.NewsDTO;
import raf.rs.rafnews_web_2023.dto.news.NewsPreviewDTO;
import raf.rs.rafnews_web_2023.model.News;
import raf.rs.rafnews_web_2023.service.NewsService;

import javax.inject.Inject;
import javax.validation.Valid;
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
    @Path("/filter/{category}/{trending}/{dateOrder}/{pageIndex}/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsPreviewDTO> filterNews(
            @PathParam("category") int categoryId,
            @PathParam("trending") boolean trending,
            @PathParam("dateOrder") String dateOrder,
            @PathParam("pageIndex") int pageIndex,
            @PathParam("pageSize") int pageSize) {
        System.out.println(categoryId + "     " + dateOrder + ' ' + trending + ' ' + pageIndex + ' ' + pageSize);

        return newsService.filterSearch(categoryId, dateOrder, trending, pageIndex, pageSize);
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
    public NewsDTO addNews(@Valid News news) {

        System.out.println(news.toString());
        return newsService.addNews(news);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public NewsPreviewDTO editNews(@Valid NewsPreviewDTO news, @PathParam("id") int id) {
        return newsService.editNews(news);
    }

    @DELETE
    @Path("/id/{id}")
    public void deleteNews(@PathParam("id") int id) {
        newsService.deleteNews(id);
    }

    @POST
    @Path("/visitCount/{newsId}")
    public void incrementVisitCount(@PathParam("newsId") int newsId) {
        System.out.println("AAAAAAAAAAAAAAA " + newsId);
        newsService.incrementVisitedCount(newsId);
    }
}
