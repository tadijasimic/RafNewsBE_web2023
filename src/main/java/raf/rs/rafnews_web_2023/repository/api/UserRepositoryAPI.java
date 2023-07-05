package raf.rs.rafnews_web_2023.repository.api;


import raf.rs.rafnews_web_2023.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepositoryAPI {


    List<User> allUsers();

    List<User> usersForPage(int pageIndex, int pageSize);

    User addUser(User user) throws SQLException;

    void deleteUser(User user);

    User editUser(User user);


    User searchUserByEmail(String email);

    User searchUserById(int id);

    enum ColumnNames {

        ID(1, "id", Integer.class),
        EMAIL(2, "email", String.class),
        NAME(3, "first_name", String.class),
        SURNAME(4, "last_name", String.class),
        PASSWORD(5, "password", String.class),
        ROLE(6, "role", String.class),
        STATUS(7, "status", String.class);

        public final Class<?> typeClass;
        public final int column_index;
        public final String column_name;

        ColumnNames(int index, String name, Class<?> typeClass) {
            this.column_index = index;
            this.column_name = name;
            this.typeClass = typeClass;
        }

        public static String buildColumnsInsertQuery(ColumnNames... without) {
            StringBuilder stringBuilder = new StringBuilder("(");
            ColumnNames[] columnNames = values();
            //pocinje od 1 znaci id se uvek preskace
            for (int i = 1; i < columnNames.length - 1; i++) {
                if(without.length == 1 && without[0] ==  columnNames[i])
                    continue;
                stringBuilder.append(columnNames[i].column_name).append(", ");
            }
            stringBuilder.append(columnNames[columnNames.length - 1].column_name).append(")");
            System.out.println(stringBuilder);
            System.out.flush();
            return stringBuilder.toString();
        }

        public static String buildColumnsUpdateQuery() {
            StringBuilder stringBuilder = new StringBuilder();
            ColumnNames[] columnNames = values();
            //isto preskaces id iteriras od 2. kolone
            for (int i = 1; i < columnNames.length - 1; i++) {
                stringBuilder.append(columnNames[i].column_name).append(" = ?, ");
            }
            stringBuilder.append(columnNames[columnNames.length - 1].column_name).append(" = ?");
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        }

        @Override
        public String toString() {
            return column_name;
        }
    }

}
