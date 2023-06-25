package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.entity.News;
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
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> allNews() {
        return newsService.allNews();
    }

    @GET
    @Path("/pageIndex/{pageIndex}/pageSize/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> newsForPage(@PathParam("pageIndex") int pageIndex, @PathParam("pageSize") int pageSize){
        return newsService.newsForPage(pageIndex, pageSize);
    }

    @GET
    @Path("/category/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> newsInCategory(@PathParam("categoryId") int categoryId){
        return newsService.newsInCategory(categoryId);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News addNews(News news) {
        return newsService.addNews(news);
    }


}
