package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.io.Reader;
import java.sql.*;

public class Util {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try {
            getDriver();
        } catch (SQLException e) {
            System.out.println("invalid Driver");
        }

    }
    // реализуйте настройку соединения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/user";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Citi2013";
    private static Connection connection;
    private static Statement statement;

    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    public static Statement getStatement(Connection connection) throws SQLException {
        statement = connection.createStatement();
        return statement;
    }

    public static void getDriver() throws SQLException {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            System.out.println("Invalid DriverManager");
        }
    }
}
