package raf.rs.rafnews_web_2023.repository.implementation;


import raf.rs.rafnews_web_2023.model.News;
import raf.rs.rafnews_web_2023.repository.api.NewsRepositoryAPI;
import raf.rs.rafnews_web_2023.repository.mysql.MySQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class NewsRepository extends MySQLRepository implements NewsRepositoryAPI {

    private static final String ENTITY_NAME = "news";

    @Override
    public List<News> allNews() {

        List<News> allNews = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDB_Connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + ENTITY_NAME + " ORDER  BY " + ColumnNames.CREATION_TIME + " DESC" );
            allNews = new ArrayList<>();
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getInt(ColumnNames.ID.column_index),
                        resultSet.getString(ColumnNames.TITLE.column_name),
                        resultSet.getString(ColumnNames.CONTENT.column_name),
                        resultSet.getInt(ColumnNames.VISITED.column_name),
                        resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                        resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                        resultSet.getInt(ColumnNames.CATEGORY_ID.column_name)
                );
                allNews.add(news);
                System.out.println(news.getCreationTime());

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

    @Override
    public List<News> newsForPage(int pageIndex, int pageSize) {
        List<News> newsForPage = null;
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
                        new News(
                                resultSet.getInt(ColumnNames.ID.column_index),
                                resultSet.getString(ColumnNames.TITLE.column_name),
                                resultSet.getString(ColumnNames.CONTENT.column_name),
                                resultSet.getInt(ColumnNames.VISITED.column_name),
                                resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                                resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                                resultSet.getInt(ColumnNames.CATEGORY_ID.column_name)
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
    public List<News> newsInCategory(int categoryId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<News> newsInCategory = null;
        try {
            connection = this.getDB_Connection();

            preparedStatement = connection.prepareStatement("SELECT * FROM " + ENTITY_NAME + " WHERE " + ColumnNames.CATEGORY_ID + " = ?");
            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();
            newsInCategory = new ArrayList<>();

            while (resultSet.next()) {
                newsInCategory.add(
                        new News(
                                resultSet.getInt(ColumnNames.ID.column_index),
                                resultSet.getString(ColumnNames.TITLE.column_name),
                                resultSet.getString(ColumnNames.CONTENT.column_name),
                                resultSet.getInt(ColumnNames.VISITED.column_name),
                                resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                                resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                                resultSet.getInt(ColumnNames.CATEGORY_ID.column_name)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return newsInCategory;
    }

    @Override
    public List<News> newsByAuthor(int authorId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<News> newsByAuthor = null;
        try {
            connection = this.getDB_Connection();

            preparedStatement = connection.prepareStatement("SELECT * FROM " + ENTITY_NAME + " WHERE " + ColumnNames.AUTHOR_ID + " = ?");
            preparedStatement.setInt(1, authorId);
            resultSet = preparedStatement.executeQuery();
            newsByAuthor = new ArrayList<>();

            while (resultSet.next()) {
                newsByAuthor.add(
                        new News(
                                resultSet.getInt(ColumnNames.ID.column_index),
                                resultSet.getString(ColumnNames.TITLE.column_name),
                                resultSet.getString(ColumnNames.CONTENT.column_name),
                                resultSet.getInt(ColumnNames.VISITED.column_name),
                                resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                                resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                                resultSet.getInt(ColumnNames.CATEGORY_ID.column_name)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }

        return newsByAuthor;
    }


    @Override
    public News addNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getDB_Connection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO " + ENTITY_NAME + "  " + ColumnNames.buildColumnsInsertQuery() + " VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getContent());
            preparedStatement.setInt(3, news.getVisited());
            preparedStatement.setTimestamp(4, news.getCreationTime());
            preparedStatement.setInt(5, news.getAuthorId());
            preparedStatement.setInt(6, news.getCategoryId());

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


    @Override
    public News findById(int newsId) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDB_Connection();
            statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM " + ENTITY_NAME + " WHERE " + ColumnNames.ID + " = " + newsId;

            resultSet = statement.executeQuery(sqlQuery);
            if (resultSet.next()) {

                return new News(
                        resultSet.getInt(ColumnNames.ID.column_index),
                        resultSet.getString(ColumnNames.TITLE.column_name),
                        resultSet.getString(ColumnNames.CONTENT.column_name),
                        resultSet.getInt(ColumnNames.VISITED.column_name),
                        resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                        resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                        resultSet.getInt(ColumnNames.CATEGORY_ID.column_name)

                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public News editNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.getDB_Connection();

            preparedStatement = connection.prepareStatement(
                    "UPDATE " + ENTITY_NAME + " SET " + ColumnNames.buildColumnsUpdateQuery() + " WHERE " + ColumnNames.ID + " = ? ");

            preparedStatement.setInt(1, news.getCategoryId());
            preparedStatement.setString(2, news.getTitle());
            preparedStatement.setString(3, news.getContent());
            preparedStatement.setInt(4, news.getVisited());
            preparedStatement.setTimestamp(5, news.getCreationTime());
            preparedStatement.setInt(6, news.getAuthorId());
            preparedStatement.setInt(7, news.getCategoryId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public void deleteNews(News news) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.getDB_Connection();
            String sql = "DELETE FROM " + ENTITY_NAME + " WHERE " + ColumnNames.ID.column_name + " = ?";
            System.out.println(sql + " " + news.getId());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, news.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

    }

    @Override
    public List<News> filterSearch(int categoryId, String dateOrder, boolean trending, int pageIndex, int pageSize) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<News> filteredNews = new ArrayList<>();

        try {
            connection = getDB_Connection();

            StringBuilder sqlQuery = new StringBuilder("SELECT * FROM " + ENTITY_NAME);

            if (categoryId != -1) {
                sqlQuery.append(" WHERE category_id = ?");
            }

            if (trending) {
                sqlQuery.append(" ORDER BY visited DESC, creation_time ").append(dateOrder);
            } else {
                sqlQuery.append(" ORDER BY creation_time ").append(dateOrder);
            }

            sqlQuery.append(" LIMIT 0, ?");

            preparedStatement = connection.prepareStatement(sqlQuery.toString());
            int parameterIndex = 1;
            if (categoryId != -1) {
                preparedStatement.setInt(parameterIndex++, categoryId);
            }
            preparedStatement.setInt(parameterIndex++, pageIndex * pageSize);
            //preparedStatement.setInt(parameterIndex, pageSize);

            // Print the SQL query before parameterization
            System.out.println(sqlQuery.toString());

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getInt("visited"),
                        resultSet.getTimestamp("creation_time"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("category_id")
                );
                filteredNews.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredNews;
    }


    public void incrementVisitedCount(int newsId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getDB_Connection();

            String sqlQuery = "UPDATE " + ENTITY_NAME + " SET " + ColumnNames.VISITED.column_name + " = " + ColumnNames.VISITED.column_name + " + 1 WHERE " + ColumnNames.ID.column_name + " = ?";

            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, newsId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
    }


}
