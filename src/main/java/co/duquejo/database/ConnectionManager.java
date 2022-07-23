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
            System.out.println(e);
        }
        return connection;
    }
}
