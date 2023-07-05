package raf.rs.rafnews_web_2023.repository.api;

import raf.rs.rafnews_web_2023.model.News;

import java.sql.Timestamp;
import java.util.List;

public interface NewsRepositoryAPI {

    List<News> allNews();

    List<News> newsForPage(int pageIndex, int pageSize);

    List<News> newsInCategory(int categoryId);

    List<News> newsByAuthor(int authorId);

    News addNews(News news);

    News findById(int newsId);

    News editNews(News news);

    void deleteNews(News news);
    public List<News> filterSearch(int categoryId, String dateOrder, boolean trending, int pageIndex, int pageSize);



    enum ColumnNames {

        ID(1, "id", Integer.class),
        TITLE(2, "title", String.class),
        CONTENT(3, "content", String.class),
        VISITED(4,"visited", Integer.class),
        CREATION_TIME(5, "creation_time", Timestamp.class),
        AUTHOR_ID(6, "author_id", Integer.class),
        CATEGORY_ID(7, "category_id", Integer.class);


        public final Class<?> typeClass;
        public final int column_index;
        public final String column_name;

        ColumnNames(int index, String name, Class<?> typeClass) {
            this.column_index = index;
            this.column_name = name;
            this.typeClass = typeClass;
        }

        public static String buildColumnsInsertQuery(String... without) {
            StringBuilder stringBuilder = new StringBuilder("(");
            ColumnNames[] names = values();

            for (int i = 1; i < names.length - 1; i++) {
                stringBuilder.append(names[i]).append(", ");
            }
            stringBuilder.append(names[names.length - 1]).append(")");
            System.out.println(stringBuilder.toString());
            System.out.flush();
            return stringBuilder.toString();
        }

        public static String buildColumnsUpdateQuery() {
            StringBuilder stringBuilder = new StringBuilder();
            ColumnNames[] names = values();

            for (int i = 1; i < names.length - 1; i++) {
                stringBuilder.append(names[i]).append(" = ?, ");
            }
            stringBuilder.append(names[names.length - 1]).append(" = ?");
            System.out.println(stringBuilder);
            return stringBuilder.toString();

        }
    }


}
