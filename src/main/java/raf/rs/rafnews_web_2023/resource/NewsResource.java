package raf.rs.rafnews_web_2023.resource;

import raf.rs.rafnews_web_2023.entity.News;
import raf.rs.rafnews_web_2023.service.NewsService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public News addNews(News news) {
        return newsService.addNews(news);
    }


}
