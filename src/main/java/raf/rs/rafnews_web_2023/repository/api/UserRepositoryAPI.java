package raf.rs.rafnews_web_2023.repository.api;



import raf.rs.rafnews_web_2023.entity.User;

import java.util.List;

public interface UserRepositoryAPI {


    List<User> getAllUsers();
    User addUser(User user);
    void deleteUser(User user);

    User editUser(User user);

    User searchUserByEmail(String email);

    User searchUserById(int id);

    enum ColumnNames
    {

        ID(1,"id",Integer.class),
        EMAIL(2,"email",String.class),
        NAME(3,"name",String.class),
        SURNAME(4, "surname",String.class),
        PASSWORD(5,"password",String.class),
        ROLE(6,"role",String.class),
        STATUS(7,"status",String.class);

        public final Class<?> typeClass;
        public final int column_index;
        public final String column_name;
        ColumnNames(int index, String name, Class<?> typeClass) {
            this.column_index = index;
            this.column_name = name;
            this.typeClass = typeClass;
        }

        public static String buildColumnsInsertQuery(String ... without)
        {
            StringBuilder stringBuilder = new StringBuilder("(");
            ColumnNames[] names = values();

            for(int i = 1; i < names.length - 1; i++) {
                stringBuilder.append(names[i]).append(", ");
            }
            stringBuilder.append(names[names.length - 1]).append(")");
            System.out.println(stringBuilder.toString());
            System.out.flush();
            return stringBuilder.toString();
        }
        public static String buildColumnsUpdateQuery()
        {
            StringBuilder stringBuilder = new StringBuilder();
            ColumnNames[] names = values();

            for(int i = 1; i < names.length - 1; i++) {
                stringBuilder.append(names[i]).append(" = ?, ");
            }
            stringBuilder.append(names[names.length - 1]).append(" = ?");
            System.out.println(stringBuilder);
            return stringBuilder.toString();
        }
        @Override
        public String toString() {
            return column_name;
        }
    }

}
