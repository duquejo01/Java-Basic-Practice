package co.duquejo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    public Connection getConnection () {
        Connection connection = null;
        try {
            // DB Connection
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/messages_app?serverTimezone=UTC",
                "messages_app",
                "12345"
            );
            if( connection != null ) {
                System.out.println("Successful connection");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
