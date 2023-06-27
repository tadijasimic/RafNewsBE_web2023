package raf.rs.rafnews_web_2023.repository.api;


import raf.rs.rafnews_web_2023.entity.Tag;

import java.util.List;

public interface TagRepositoryAPI {


    List<Tag> allTags();

    List<Tag> tagForPage(int pageIndex, int pageSize);

    List<Tag> tag(int categoryId);



    enum ColumnNames {

        ID(1, "id", Integer.class),
        NAME(2, "name", String.class);


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



