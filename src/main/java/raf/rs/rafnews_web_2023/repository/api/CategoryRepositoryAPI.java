package raf.rs.rafnews_web_2023.repository.api;


import raf.rs.rafnews_web_2023.model.entity.Category;


import java.util.List;

public interface CategoryRepositoryAPI {

    List<Category> allCategories();

    List<Category> categoriesForPage(int pageIndex, int pageSize);

    Category addCategory(Category category);

    void deleteCategory(Category category);


    Category searchCategoryByName(String name);


    enum ColumnNames {

        ID(1, "id", Integer.class),
        NAME(2, "name", String.class),
        DESCRIPTION(3, "description", String.class);


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
            ColumnNames[] columnNames = values();

            for (int i = 1; i < columnNames.length - 1; i++) {
                stringBuilder.append(columnNames[i].column_name).append(", ");
            }
            stringBuilder.append(columnNames[columnNames.length - 1].column_name).append(")");
            System.out.println(stringBuilder.toString());
            System.out.flush();
            return stringBuilder.toString();
        }

        public static String buildColumnsUpdateQuery() {
            StringBuilder stringBuilder = new StringBuilder();
            ColumnNames[] columnNames = values();

            for (int i = 1; i < columnNames.length - 1; i++) {
                stringBuilder.append(columnNames[i].column_name).append(" = ?, ");
            }
            stringBuilder.append(columnNames[columnNames.length - 1].column_name).append(" = ?");
            System.out.println(stringBuilder);
            return stringBuilder.toString();

        }
    }

}
