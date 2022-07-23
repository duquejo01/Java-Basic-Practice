package co.duquejo;

import co.duquejo.services.MessageService;

import java.util.Scanner;

public class Menu {
    public static void showMenu() {
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n:::: Basic Java Messages application ::::");
            System.out.println("1. Create message");
            System.out.println("2. List all messages");
            System.out.println("3. Edit message");
            System.out.println("4. Delete message by ID");
            System.out.println("0. Exit");
            switch ( option = sc.nextInt() ) {
                case 1:
                    MessageService.createMessage();
                    break;
                case 2:
                    MessageService.getMessages();
                    break;
                case 3:
                    MessageService.updateMessage();
                    break;
                case 4:
                    MessageService.deleteMessage();
                    break;
                default:
                    break;
            }
        } while ( option != 0 );
        sc.close(); // Close scanner
    }
}
