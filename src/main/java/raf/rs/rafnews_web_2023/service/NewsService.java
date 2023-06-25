package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.entity.News;
import raf.rs.rafnews_web_2023.repository.api.NewsRepositoryAPI;

import javax.inject.Inject;
import java.util.List;

public class NewsService {
    @Inject
    NewsRepositoryAPI newsRepository;

    public NewsService() {
        System.out.println(this);
    }

    public List<News> allNews() {
        return newsRepository.allNews();
    }

    public List<News> newsForPage(int pageIndex, int pageSize) {
        return newsRepository.newsForPage(pageIndex, pageSize);
    }

    public List<News> newsInCategory(int categoryId) {
        return newsRepository.newsInCategory(categoryId);
    }
    public News addNews(News news) {
        return newsRepository.addNews(news);
    }



}
