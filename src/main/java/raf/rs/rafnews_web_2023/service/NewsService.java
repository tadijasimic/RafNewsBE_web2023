package raf.rs.rafnews_web_2023.service;

import raf.rs.rafnews_web_2023.converter.NewsDTO_Converter;
import raf.rs.rafnews_web_2023.dto.news.NewsDTO;
import raf.rs.rafnews_web_2023.dto.news.NewsPreviewDTO;
import raf.rs.rafnews_web_2023.model.News;
import raf.rs.rafnews_web_2023.repository.api.NewsRepositoryAPI;

import javax.inject.Inject;
import java.util.List;

public class NewsService {
    @Inject
    private NewsRepositoryAPI newsRepository;

    @Inject
    private UserService userService;
    @Inject
    private CommentService commentService;

    @Inject CategoryService categoryService;

    public NewsService() {
        System.out.println(this);
    }

    public List<NewsPreviewDTO> allNews() {
        List<News> news = newsRepository.allNews();
        return NewsDTO_Converter
                .convertToNewsPreviewDTOList(
                        news,
                        userService.searchNewsAuthors(news),
                        categoryService.searchNewsCategories(news)
                );
    }

    public List<NewsPreviewDTO> newsForPage(int pageIndex, int pageSize) {
        List<News> news = newsRepository.newsForPage(pageIndex, pageSize);
        return NewsDTO_Converter
                .convertToNewsPreviewDTOList(
                        news,
                        userService.searchNewsAuthors(news),
                        categoryService.searchNewsCategories(news)
                );
    }

    public List<NewsPreviewDTO> newsInCategory(int categoryId) {
        List<News> news = newsRepository.newsInCategory(categoryId);
        return NewsDTO_Converter
                .convertToNewsPreviewDTOList(
                        news,
                        userService.searchNewsAuthors(news),
                        categoryService.searchNewsCategories(news)
                );
    }

    public NewsDTO findById(int id) {
        News news = newsRepository.findById(id);
        return NewsDTO_Converter
                .convertToNewsDTO(
                        news,
                        userService.searchAuthor(news),
                        categoryService.searchCategoryById(news.getCategoryId()),
                        commentService.commentsOnNews(news.getId())
                );
    }

    public NewsDTO addNews(NewsDTO newsDTO) {
        News news = NewsDTO_Converter.convertToNews(newsDTO);
        news = newsRepository.addNews(news);

        return NewsDTO_Converter
                .convertToNewsDTO(
                        news,
                        userService.searchAuthor(news),
                        categoryService.searchCategoryById(news.getCategoryId()),
                        commentService.commentsOnNews(news.getId())

                );
    }


}
