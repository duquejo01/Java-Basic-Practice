package co.duquejo;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        ConnectionManager connection =  new ConnectionManager();
        try (Connection cnx = connection.getConnection()){
        } catch ( Exception e ) {
            System.out.println(e);
        }
    }
}
