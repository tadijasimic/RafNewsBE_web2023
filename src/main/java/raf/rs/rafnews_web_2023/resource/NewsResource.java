package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.model.dto.NewsDTO;
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
    public List<NewsDTO> allNews() {
        return newsService.allNews();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsDTO> newsForPage(@QueryParam("pageIndex") int pageIndex, @QueryParam("pageSize") int pageSize){
        return newsService.newsForPage(pageIndex, pageSize);
    }

    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NewsDTO> newsInCategory(@PathParam("categoryId") int categoryId){
        return newsService.newsInCategory(categoryId);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public NewsDTO addNews(NewsDTO news) {
        return newsService.addNews(news);
    }


}
