package raf.rs.rafnews_web_2023.repository.mysql;

import java.sql.*;
import java.util.Optional;

public class MySQLRepository {

    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DB_NAME = "raf_news_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "mivanmajer99";
    private static final String DB_DIR_PATH = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;


    public MySQLRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getDB_Connection() throws SQLException {
        return DriverManager.getConnection(DB_DIR_PATH, DB_USERNAME, DB_PASSWORD);

    }
    protected void closeStatement(Statement statement) {
        try {
            Optional.of(statement).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeResultSet(ResultSet resultSet) {
        try {
            Optional.of(resultSet).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void closeConnection(Connection connection) {
        try {
            Optional.of(connection).get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
