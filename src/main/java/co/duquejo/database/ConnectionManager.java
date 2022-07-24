package co.duquejo.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class ConnectionManager {
    public static Connection getConnection () {
        Dotenv dotenv = Dotenv.configure().directory("./config").load();
        Connection connection = null;
        try {
            // DB Connection
            connection = DriverManager.getConnection(
                    String.format(
                            "jdbc:mysql://%1$s:%2$s/%3$s?autoReconnect=true&serverTimezone=UTC",
                            dotenv.get("DATABASE_HOST"),
                            dotenv.get("DATABASE_PORT"),
                            dotenv.get("DATABASE_NAME")
                    ),
                    dotenv.get("DATABASE_USER"),
                    dotenv.get("DATABASE_PASSWORD")
            );

            if( connection != null ) {
                System.out.println("Successful connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static int executeUpdate ( String query, Object[] params ) throws SQLException {
        int i = 1;
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement ps = connection.prepareStatement( query );
        for ( Object p: params ) {
            if( p.getClass().getName().equals("java.lang.String") ) {
                ps.setString( i, (String) p);
            } else if ( p.getClass().getName().equals("java.lang.Integer") ) {
                ps.setInt( i, (Integer) p);
            }
            i++;
        }
        int affectedRows = ps.executeUpdate();
        connection.close();
        return affectedRows;
    }

    public static void executeQuery ( String query ) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        ResultSet rs;
        PreparedStatement ps = connection.prepareStatement( query );
        rs = ps.executeQuery();
        while( rs.next() ) {
            System.out.println("ID: " + rs.getInt("ID" ) );
            System.out.println("Message: " + rs.getString("message" ) );
            System.out.println("Author: " + rs.getString("author" ) );
            System.out.println("Date: " + rs.getTimestamp("date_created" ) );
            System.out.println("\n");
        }
        connection.close();
    }
}
