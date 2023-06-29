package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.News;

public class NewsPreviewDTO {

    private int id;
    private AuthorDTO author;
    private String title;

    private String newsPreview;

    public NewsPreviewDTO() {

    }

    public NewsPreviewDTO(News news, AuthorDTO authorDTO) {
        id = news.getId();
        author = authorDTO;
        title = news.getTitle();
        newsPreview = news.getContent().substring(0, Math.min(news.getContent().length(), 500));
    }

    public int getId() {
        return id;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public String getNewsPreview() {
        return newsPreview;
    }

    public String getTitle() {
        return title;
    }
}
