package com.tamerb.jdbc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteDataDemo {

    public static final String DB_URL = "jdbc:mysql://localhost/movie_characters";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "mysql";
    static Connection connection = null;
    static Statement stmt = null;

    public static void main(String[] args) throws SQLException {

        //1- Make a database connection
        connection = DriverManager
                .getConnection(DB_URL, DB_USER, DB_PASSWORD);

        //2- Execute the SQL Delete Query
        stmt = connection.createStatement();
        stmt.execute("DELETE FROM mov_chars WHERE id = '19' ");
    }
}
