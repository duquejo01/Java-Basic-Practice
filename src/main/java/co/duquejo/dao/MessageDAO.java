package co.duquejo.dao;

import co.duquejo.database.ConnectionManager;
import co.duquejo.models.Message;

import java.sql.SQLException;

public class MessageDAO {

    private static final String tableName = "messages";

    public static int create(Message message ) throws SQLException {
        String query = String.format( "INSERT INTO %1$s (message, author) VALUES( ?, ?)", tableName );
        Object[] params = new Object[]{ message.getMessage(), message.getAuthor() };
        return ConnectionManager.executeUpdate( query, params );
    }

    public static void read() throws SQLException {
        String query = String.format( "SELECT ID, message, author, date_created FROM %1$s", tableName );
        ConnectionManager.executeQuery( query );
    }

    public static int delete( int ID ) throws SQLException {
        String query = String.format( "DELETE FROM %1$s WHERE ID = ?", tableName );
        Object[] params = new Object[]{ ID };
        return ConnectionManager.executeUpdate( query, params );
    }

    public static int update( Message message ) throws SQLException {
        String query = String.format( "UPDATE %1$s SET message = ?, author = ? WHERE ID = ?", tableName );
        Object[] params = new Object[]{ message.getMessage(), message.getAuthor(), message.getID() };
        return ConnectionManager.executeUpdate( query, params );
    }
}
