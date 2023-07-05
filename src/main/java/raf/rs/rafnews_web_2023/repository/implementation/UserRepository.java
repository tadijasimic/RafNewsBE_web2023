package raf.rs.rafnews_web_2023.repository.implementation;

import raf.rs.rafnews_web_2023.model.User;
import raf.rs.rafnews_web_2023.repository.api.UserRepositoryAPI;
import raf.rs.rafnews_web_2023.repository.mysql.MySQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends MySQLRepository implements UserRepositoryAPI {

    private static final String ENTITY_NAME = "user";

    @Override
    public List<User> allUsers() {

        List<User> allUsers = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDB_Connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + ENTITY_NAME);
            while (resultSet.next()) {
                allUsers.add(
                        new User(
                                resultSet.getInt(ColumnNames.ID.column_index),
                                resultSet.getString(ColumnNames.EMAIL.column_name),
                                resultSet.getString(ColumnNames.NAME.column_name),
                                resultSet.getString(ColumnNames.SURNAME.column_name),
                                resultSet.getString(ColumnNames.PASSWORD.column_name),
                                resultSet.getString(ColumnNames.ROLE.column_name),
                                resultSet.getString(ColumnNames.STATUS.column_name)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return allUsers;
    }

    @Override
    public List<User> usersForPage(int pageIndex, int pageSize) {
        List<User> newsForPage = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDB_Connection();
            statement = connection.createStatement();
            int startIndex = pageIndex * pageSize;
            String sqlQuery = "SELECT * FROM " + ENTITY_NAME + " LIMIT " + startIndex + ", " + pageSize;
            resultSet = statement.executeQuery(sqlQuery);
            newsForPage = new ArrayList<>();
            while (resultSet.next()) {
                newsForPage.add(
                        new User(
                                resultSet.getInt(ColumnNames.ID.column_index),
                                resultSet.getString(ColumnNames.EMAIL.column_name),
                                resultSet.getString(ColumnNames.NAME.column_name),
                                resultSet.getString(ColumnNames.SURNAME.column_name),
                                resultSet.getString(ColumnNames.PASSWORD.column_name),
                                resultSet.getString(ColumnNames.ROLE.column_name),
                                resultSet.getString(ColumnNames.STATUS.column_name)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return newsForPage;
    }

    @Override
    public User addUser(User user) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getDB_Connection();

            String[] generatedColumns = {"id"};

            String sql = "INSERT INTO " + ENTITY_NAME + " " + ColumnNames.buildColumnsInsertQuery()
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getStatus().name());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId((int) resultSet.getLong(1));
            } else
                throw new RuntimeException("adding user failed");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return user;
    }


    @Override
    public void deleteUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.getDB_Connection();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM " + ENTITY_NAME + " WHERE " + ColumnNames.ID + " = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

    }

    @Override
    public User editUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getDB_Connection();

            preparedStatement = connection.prepareStatement(
                    "UPDATE " + ENTITY_NAME + " SET " + ColumnNames.buildColumnsUpdateQuery() + " WHERE " + ColumnNames.ID + " = ? ");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole().name());
            preparedStatement.setString(7, user.getStatus().name());
            preparedStatement.setInt(8, user.getId());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public User searchUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = this.getDB_Connection();

            preparedStatement = connection.prepareStatement("SELECT * FROM " + ENTITY_NAME + " WHERE " + ColumnNames.EMAIL + " = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                user = new User(
                        resultSet.getInt(ColumnNames.ID.column_index),
                        resultSet.getString(ColumnNames.EMAIL.column_name),
                        resultSet.getString(ColumnNames.NAME.column_name),
                        resultSet.getString(ColumnNames.SURNAME.column_name),
                        resultSet.getString(ColumnNames.PASSWORD.column_name),
                        resultSet.getString(ColumnNames.ROLE.column_name),
                        resultSet.getString(ColumnNames.STATUS.column_name)
                );


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return user;
    }

    @Override
    public User searchUserById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = this.getDB_Connection();

            preparedStatement = connection.prepareStatement("SELECT * FROM " + ENTITY_NAME + " WHERE " + ColumnNames.ID + " = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
                user = new User(
                        resultSet.getInt(ColumnNames.ID.column_index),
                        resultSet.getString(ColumnNames.EMAIL.column_name),
                        resultSet.getString(ColumnNames.NAME.column_name),
                        resultSet.getString(ColumnNames.SURNAME.column_name),
                        resultSet.getString(ColumnNames.PASSWORD.column_name),
                        resultSet.getString(ColumnNames.ROLE.column_name),
                        resultSet.getString(ColumnNames.STATUS.column_name)
                );


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return user;
    }

}




