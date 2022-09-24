package com.tamerb.jdbc.transaction;

import java.sql.*;

public class Main {

    private final String url = "jdbc:postgresql://localhost/dvdrental";
    private final String user = "postgres";
    private final String password = "postgres";


    //1- Connect to the PostgreSQL database
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    //2-  Close a AutoCloseable object
    private Main close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return this;
    }

    //3- Add actor
    public void addActorAndAssignFilm(Actor actor, int filmId) {


        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;

        // insert an actor into the actor table
        String SQLInsertActor = "INSERT INTO actor(first_name,last_name) "
                + "VALUES(?,?)";

        // assign actor to a film
        String SQLAssignActor = "INSERT INTO film_actor(actor_id,film_id) "
                + "VALUES(?,?)";

        int actorId = 0;
        try {
            // connect to the database
            conn = connect();
            conn.setAutoCommit(false);

            // add actor
            pstmt = conn.prepareStatement(SQLInsertActor,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, actor.getFirstName());
            pstmt.setString(2, actor.getLastName());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // get actor id
                rs = pstmt.getGeneratedKeys();

                if (rs.next()) {
                    actorId = rs.getInt(1);
                    if (actorId > 0) {
                        pstmt2 = conn.prepareStatement(SQLAssignActor);
                        pstmt2.setInt(1, actorId);
                        pstmt2.setInt(2, filmId);
                        pstmt2.executeUpdate();
                    }
                }
            } else {
                // rollback the transaction if the insert failed
                conn.rollback();
            }

            // commit the transaction if everything is fine
            conn.commit();

            System.out.printf("The actor was inserted with id %d and "
                    + "assigned to the film %d%n", actorId, filmId);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // roll back the transaction
            System.out.println("Rolling back the transaction...");
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } finally {
            this.close(rs)
                    .close(pstmt)
                    .close(pstmt2)
                    .close(conn);
        }
    }


    public static void main(String[] args)  {
        Main main = new Main();
        main.addActorAndAssignFilm(new Actor("Tamer", "Bilici"), 9);
    }
}
