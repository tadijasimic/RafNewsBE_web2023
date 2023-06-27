package raf.rs.rafnews_web_2023.implementation;

import raf.rs.rafnews_web_2023.model.entity.Comment;
import raf.rs.rafnews_web_2023.repository.api.CommentRepositoryAPI;
import raf.rs.rafnews_web_2023.repository.mysql.MySQLRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository extends MySQLRepository implements CommentRepositoryAPI {


    private static final String ENTITY_NAME = "comment";

    @Override
    public List<Comment> allComments() {

        List<Comment> allComments = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDB_Connection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + ENTITY_NAME);
            allComments = new ArrayList<>();
            while (resultSet.next()) {
                allComments.add(
                        new Comment(
                                resultSet.getInt(ColumnNames.ID.column_name),
                                resultSet.getString(ColumnNames.CONTENT.column_name),
                                resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                                resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                                resultSet.getInt(ColumnNames.NEWS_ID.column_name)
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
        return allComments;
    }

    @Override
    public List<Comment> commentsForPage(int pageIndex, int pageSize) {
        List<Comment> commentsForPage = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getDB_Connection();
            statement = connection.createStatement();
            int startIndex = pageIndex * pageSize;
            String sqlQuery = "SELECT * FROM " + ENTITY_NAME + " LIMIT " + startIndex + ", " + pageSize;
            resultSet = statement.executeQuery(sqlQuery);
            commentsForPage = new ArrayList<>();
            while (resultSet.next()) {
                commentsForPage.add(
                        new Comment(
                                resultSet.getInt(ColumnNames.ID.column_name),
                                resultSet.getString(ColumnNames.CONTENT.column_name),
                                resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                                resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                                resultSet.getInt(ColumnNames.NEWS_ID.column_name)
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
        return commentsForPage;
    }

    @Override
    public List<Comment> commentsOnNews(int postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Comment> commentsOnPost = null;
        try {
            connection = this.getDB_Connection();

            preparedStatement = connection.prepareStatement("SELECT * FROM " + ENTITY_NAME + " WHERE " + ColumnNames.NEWS_ID + " = ?");
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();
            commentsOnPost = new ArrayList<>();

            while (resultSet.next()) {
                commentsOnPost.add(
                        new Comment(
                                resultSet.getInt(ColumnNames.ID.column_name),
                                resultSet.getString(ColumnNames.CONTENT.column_name),
                                resultSet.getTimestamp(ColumnNames.CREATION_TIME.column_name),
                                resultSet.getInt(ColumnNames.AUTHOR_ID.column_name),
                                resultSet.getInt(ColumnNames.NEWS_ID.column_name)
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

        return commentsOnPost;


    }

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.getDB_Connection();

            String[] generatedColumns = {"id"};
            String sql = "INSERT INTO " + ENTITY_NAME + " " + ColumnNames.buildColumnsInsertQuery() + " VALUES(?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setString(2, comment.getCreationTime().toString());
            preparedStatement.setInt(3, comment.getAuthorId());
            preparedStatement.setInt(4, comment.getNewsId());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(ColumnNames.ID.column_index));
                return comment;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
        return null;

    }

    @Override
    public void deleteComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.getDB_Connection();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM " + ENTITY_NAME + " WHERE " + ColumnNames.ID.column_name + " = ?");
            preparedStatement.setInt(1, comment.getId());
            if(preparedStatement.executeUpdate() > 1)
                throw new RuntimeException("Comment delete failed.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
