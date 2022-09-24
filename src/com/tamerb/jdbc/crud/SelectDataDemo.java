package com.tamerb.jdbc.crud;

import java.sql.*;

public class SelectDataDemo {

    public static final String DB_URL = "jdbc:mysql://localhost/movie_characters";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "mysql";
    static Connection connection = null;
    static Statement stmt = null;

    public static void main(String[] args) throws SQLException {

        //1- Make a database connection
        connection = DriverManager
                .getConnection(DB_URL, DB_USER, DB_PASSWORD);

        //2- Execute the SQL Query
        stmt = connection.createStatement();
        ResultSet resultSet = stmt
                .executeQuery("SELECT id, char_name, char_appearance FROM mov_chars ");
        while (resultSet.next()) {
            System.out.print("\t\tid : "
                    + resultSet.getString(1));
            System.out.print("\t\tname : "
                    + resultSet.getString(2));
            System.out.println("\t\tclass : "
                    + resultSet.getString(3));
        }
    }
}
