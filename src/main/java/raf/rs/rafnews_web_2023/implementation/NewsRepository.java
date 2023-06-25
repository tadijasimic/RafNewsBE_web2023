package raf.rs.rafnews_web_2023.implementation;


import raf.rs.rafnews_web_2023.entity.News;
import raf.rs.rafnews_web_2023.repository.api.NewsRepositoryAPI;
import raf.rs.rafnews_web_2023.repository.mysql.MySQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository extends MySQLRepository implements NewsRepositoryAPI {

    private static final String ENTITY_NAME = "news";
    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getDB_Connection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO " + ENTITY_NAME + " " + ColumnNames.buildColumnsInsertQuery() +
                            " VALUES(?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getAuthorId());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                 news.setId(resultSet.getInt(ColumnNames.ID.column_index));
            } else
                throw new RuntimeException("adding user failed");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return news;
    }


    public List<News> allNews() {

        List<News> allNews = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDB_Connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + ENTITY_NAME);
            allNews = new ArrayList<>();
            while (resultSet.next()) {
                allNews.add(
                        new News(
                                resultSet.getInt(ColumnNames.ID.column_index),
                                resultSet.getString(ColumnNames.TITLE.column_name),
                                resultSet.getString(ColumnNames.TITLE.column_name),
                                resultSet.getInt(ColumnNames.AUTHOR_ID.column_name)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return allNews;
    }




}
