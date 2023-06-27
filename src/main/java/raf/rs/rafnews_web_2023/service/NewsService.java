package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.model.entity.News;
import raf.rs.rafnews_web_2023.dto.NewsDTO;
import raf.rs.rafnews_web_2023.repository.api.NewsRepositoryAPI;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class NewsService {
    @Inject
    NewsRepositoryAPI newsRepository;

    public NewsService() {
        System.out.println(this);
    }

    public List<NewsDTO> allNews() {
        return buildListDTO(newsRepository.allNews());
    }

    public List<NewsDTO> newsForPage(int pageIndex, int pageSize) {
        return buildListDTO(newsRepository.newsForPage(pageIndex, pageSize));
    }

    public List<NewsDTO> newsInCategory(int categoryId) {
        return buildListDTO(newsRepository.newsInCategory(categoryId));
    }

    public NewsDTO addNews(NewsDTO newsDTO) {
        News news = new News(
                newsDTO.getId(),
                newsDTO.getTitle(),
                newsDTO.getContent(),
                0,
                newsDTO.getCreationTime(),
                newsDTO.getAuthorId(),
                newsDTO.getCategoryId()
        );
        return newsRepository.addNews(news).buildDTO();
    }

    private List<NewsDTO> buildListDTO(List<News> newsList) {
        List<NewsDTO> DTOs = new ArrayList<>();
        for (News news : newsList) {
            DTOs.add(news.buildDTO());
        }
        return DTOs;
    }


}
