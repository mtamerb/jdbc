package com.tamerb.jdbc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataDemo {

    public static final String DB_URL = "jdbc:mysql://localhost/movie_characters";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "mysql";
    static Connection connection = null;
    static Statement stmt = null;


    public static void main(String[] args) throws SQLException {

        //1- Make a database connection
        connection = DriverManager
                .getConnection(DB_URL, DB_USER, DB_PASSWORD);

        //2- Execute the SQL Insert Query
        stmt = connection.createStatement();
        stmt.execute("INSERT INTO mov_chars ( char_name, char_appearance) VALUES"
                + "('The Joker      ', 'The Dark Knight'),"
                + "('John McClane   ', 'The Die Hard'),"
                + "('Tyler Durden   ', 'Fight Club'),"
                + "('Darth Vader    ', 'Star Wars'),"
                + "('Keyser Söze    ', 'The Usual Suspects'),"
                + "('Michel Corleone', 'The Godfather'),"
                + "('Forrest Gump   ', 'Forrest Gump'),"
                + "('Rick Blaine    ', 'Casablanca'),"
                + "('Doc Brown      ', 'The Back To The Future'),"
                + "('Lester Burnham ', 'American Beauty '),"
                + "('Amy Dunne      ', 'Gone Girl');"
        );
    }
}
