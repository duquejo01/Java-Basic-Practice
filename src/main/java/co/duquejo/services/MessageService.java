package co.duquejo.services;

import co.duquejo.dao.MessageDAO;
import co.duquejo.models.Message;

import java.sql.SQLException;
import java.util.Scanner;

public class MessageService {
    public static void createMessage() {

        Scanner sc = new Scanner( System.in );

        System.out.println("Write a message:");
        String content = sc.nextLine();

        System.out.println("Your name:");
        String author = sc.nextLine();

        Message message = new Message();
        message.setMessage(content);
        message.setAuthor(author);

        // DAO Manager
        try {
            int rows = MessageDAO.create(message);
            System.out.println(":: (" + rows + ") Message created.");
        } catch( SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getMessages() {
        try {
            MessageDAO.read();
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteMessage() {
        Scanner sc = new Scanner( System.in );

        System.out.println("Message ID for delete:");
        int id = sc.nextInt();

        try {
            int rows = MessageDAO.delete( id );
            System.out.println(":: (" + rows + ") message(s) deleted.");
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateMessage() {
        Scanner sc = new Scanner( System.in );

        System.out.println("Write a message (update):");
        String content = sc.nextLine();

        System.out.println("Author's name: (update)");
        String author = sc.nextLine();

        System.out.println("Message ID for update:");
        int id = sc.nextInt();

        Message message = new Message();
        message.setMessage( content );
        message.setAuthor( author );
        message.setID( id );

        try {
            int rows = MessageDAO.update( message );
            System.out.println(":: (" + rows + ") message(s) updated.");
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
}
