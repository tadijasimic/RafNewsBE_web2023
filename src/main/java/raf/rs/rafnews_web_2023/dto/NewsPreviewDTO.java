package raf.rs.rafnews_web_2023.dto;

import raf.rs.rafnews_web_2023.model.News;

public class NewsPreviewDTO {

    private int id;
    private String title;
    private String contentPreview;
    private AuthorDTO author;

    public NewsPreviewDTO() {

    }

    public NewsPreviewDTO(News news, AuthorDTO authorDTO) {
        id = news.getId();
        title = news.getTitle();
        contentPreview = news.getContent().substring(0, Math.min(news.getContent().length(), 500));
        author = authorDTO;

    }

    public int getId() {
        return id;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public String getContentPreview() {
        return contentPreview;
    }

    public String getTitle() {
        return title;
    }
}
