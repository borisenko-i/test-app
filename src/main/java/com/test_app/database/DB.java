package com.test_app.database;

import com.test_app.resources.Entry;

import java.sql.*;

public class DB {

    public static Connection connect(String url, String user, String password) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager
                    .getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully." + DbConnection.url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage() + DbConnection.url);
        }

        return conn;
    }

    public static long insertData(Entry entry) {
        String SQL = "INSERT INTO request_data (data) VALUES (?)";

        long id = 0;

        try (Connection connection = connect(DbConnection.url, DbConnection.user, DbConnection.password);
             PreparedStatement pstmt = connection.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, entry.data);

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return id;
    }
}