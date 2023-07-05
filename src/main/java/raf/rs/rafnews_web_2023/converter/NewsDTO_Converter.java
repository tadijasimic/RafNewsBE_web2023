package raf.rs.rafnews_web_2023.converter;

import raf.rs.rafnews_web_2023.dto.category.CategoryDTO;
import raf.rs.rafnews_web_2023.dto.comment.CommentDTO;
import raf.rs.rafnews_web_2023.dto.news.NewsDTO;
import raf.rs.rafnews_web_2023.dto.news.NewsPreviewDTO;
import raf.rs.rafnews_web_2023.dto.user.AuthorDTO;
import raf.rs.rafnews_web_2023.model.News;

import java.util.ArrayList;
import java.util.List;


public abstract class NewsDTO_Converter {

    public static NewsDTO convertToNewsDTO(News news, AuthorDTO author, CategoryDTO category, List<CommentDTO> comments) {
        if (news == null || author == null || category == null)
            return null;
        return new NewsDTO(news, author, category, comments);

    }

    /* public static List<NewsDTO> convertToNewsDTOList
             (
                     List<News> news,
                     List<AuthorDTO> authors,
                     List<List<CommentDTO>> commentsOnPosts
             ) {
         List<NewsDTO> dtoList = new ArrayList<>();
         if (news.size() != authors.size())
             return null;
         for (int i = 0; i < news.size(); i++) {
             dtoList.add(
                     convertToNewsDTO(
                             news.get(i),
                             authors.get(i),
                             commentsOnPosts.get(i)
                     )
             );
         }
         return dtoList;
     }
 */
    public static NewsPreviewDTO convertToNewsPreviewDTO(News news, AuthorDTO author, CategoryDTO category) {
        if (news == null || author == null || category == null)
            return null;
        return new NewsPreviewDTO(news, author, category);
    }

    public static List<NewsPreviewDTO> convertToNewsPreviewDTOList(List<News> news, List<AuthorDTO> authors, List<CategoryDTO> categories) {
        if (news == null || authors == null || categories == null)
            return null;
        List<NewsPreviewDTO> dtoList = new ArrayList<>();
        if (news.size() != authors.size())
            return null;
        for (int i = 0; i < news.size(); i++) {
            dtoList.add(convertToNewsPreviewDTO(news.get(i), authors.get(i), categories.get(i)));
        }
        return dtoList;
    }


    public static News convertToNews(NewsDTO dto) {
        if (dto == null)
            return null;
        return new News(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                0,
                dto.getCreationTime(),
                dto.getAuthor().getId(),
                0
        );
    }

    public static News convertToNews(NewsPreviewDTO dto) {
        if (dto == null)
            return null;
        return new News(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                0,
                dto.getCreationTime(),
                dto.getAuthor().getId(),
                0
        );
    }
}
