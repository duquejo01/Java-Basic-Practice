package co.duquejo.dao;

import co.duquejo.database.ConnectionManager;
import co.duquejo.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDAO {

    private static final String tableName = "messages";

    public static void create( Message message ) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement ps;
        String query = String.format( "INSERT INTO %1$s (message, author) VALUES( ?, ?)", tableName );
        ps = connection.prepareStatement( query );
        ps.setString( 1, message.getMessage() );
        ps.setString( 2, message.getAuthor() );
        ps.executeUpdate();
        connection.close();
    }

    public static void read() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String query = String.format( "SELECT ID, message, author, date_created FROM %1$s", tableName );
        ps = connection.prepareStatement( query );
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

    public static int delete( int ID ) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement ps;
        String query = String.format( "DELETE FROM %1$s WHERE ID = ?", tableName );
        ps = connection.prepareStatement( query );
        ps.setInt( 1, ID );
        int affectedRows = ps.executeUpdate();
        connection.close();
        return affectedRows;
    }

    public static int update( Message message ) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement ps;
        String query = String.format( "UPDATE %1$s SET message = ?, author = ? WHERE ID = ?", tableName );
        ps = connection.prepareStatement( query );
        ps.setString( 1, message.getMessage() );
        ps.setString( 2, message.getAuthor() );
        ps.setInt( 3, message.getID() );
        int affectedRows = ps.executeUpdate();
        connection.close();
        return affectedRows;
    }
}
