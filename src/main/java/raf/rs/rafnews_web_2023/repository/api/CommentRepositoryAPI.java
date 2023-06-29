package raf.rs.rafnews_web_2023.repository.api;

import raf.rs.rafnews_web_2023.model.Comment;

import java.sql.Timestamp;
import java.util.List;

public interface CommentRepositoryAPI {

    List<Comment> allComments();

    List<Comment> commentsOnNews(int newsId);

    List<Comment> commentsOnNews(int newsId, int pageIndex, int pageSize);

    Comment addComment(Comment comment);

    void deleteComment(Comment comment);


    enum ColumnNames {
        ID(1, "id", Integer.class),
        CONTENT(3, "content", String.class),
        CREATION_TIME(4, "creation_time", Timestamp.class),
        AUTHOR_ID(5, "author_id", Integer.class),
        NEWS_ID(6, "news_id", Integer.class);


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
