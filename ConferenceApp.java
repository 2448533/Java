/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NANDANA BIJU
 */
import java.util.Scanner;

public class ConferenceApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AttendeeManager manager = new AttendeeManager();

        while (true) {
            System.out.println("Welcome to the Conference Registration System");
            System.out.println("1. Add Attendee");
            System.out.println("2. Edit Attendee");
            System.out.println("3. Delete Attendee");
            System.out.println("4. Search Attendee");
            System.out.println("5. Generate Attendee Statistics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter full name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter contact number: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter country: ");
                    String country = scanner.nextLine();
                    manager.addAttendee(name, email, contact, country);
                    break;
                case 2:
                    System.out.print("Enter attendee ID to edit: ");
                    int attendeeId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new contact number: ");
                    String newContact = scanner.nextLine();
                    manager.editAttendee(attendeeId, newEmail, newContact);
                    break;
                case 3:
                    System.out.print("Enter attendee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    manager.deleteAttendee(deleteId);
                    break;
                case 4:
                    System.out.print("Enter name or country to search: ");
                    String searchCriteria = scanner.nextLine();
                    manager.searchAttendee(searchCriteria);
                    break;
                case 5:
                    manager.generateStatistics();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }
}

