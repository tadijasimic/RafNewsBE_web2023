package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.NewsDTO;
import raf.rs.rafnews_web_2023.model.entity.News;

public class NewsDTO_Converter {


    private NewsDTO convertToNewsDTO(News news){
        return new NewsDTO(news);
    }

}
