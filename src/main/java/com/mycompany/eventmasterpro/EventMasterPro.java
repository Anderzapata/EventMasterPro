
package com.mycompany.eventmasterpro;

import java.io.*;
import java.util.*;

public class EventMasterPro {

    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static Event currentEvent;
    private static final List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to EventMaster Pro!");

        loadUsersFromFile("users.txt");
        loadDefaultUsersIfNeeded();

       
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                currentUser = user;
                System.out.println(user.getRole() + " authenticated!");
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Authentication failed!");
            return;
        }

        currentEvent = Event.loadFromFile("event.txt");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Create Event");
            System.out.println("2. Add Tickets");
            System.out.println("3. Sell Tickets");
            System.out.println("4. View Event Details");
            System.out.println("5. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    addTickets();
                    break;
                case 3:
                    sellTickets();
                    break;
                case 4:
                    viewEventDetails();
                    break;
                case 5:
                    exit = true;
                    if (currentEvent != null) {
                        saveSummaryToFile("event_summary.txt");
                    }
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void loadDefaultUsersIfNeeded() {
        if (users.isEmpty()) {
            User admin = new User("admin", "admin123", "admin");
            User paula = new User("Paula", "Admin123", "admin");
            users.add(admin);
            users.add(paula);
            admin.saveToFile("users.txt");
            paula.saveToFile("users.txt");
        }
    }

    private static void loadUsersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromDataString(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("No user file found. It will be created.");
        }
    }

    private static void createEvent() {
        System.out.print("Enter event name: ");
        String name = scanner.nextLine();
        System.out.print("Enter event date: ");
        String date = scanner.nextLine();
        System.out.print("Enter event time: ");
        String time = scanner.nextLine();
        System.out.print("Enter event location: ");
        String location = scanner.nextLine();

        System.out.println("Select event type: (1) Concert (2) Theater (3) Conference");
        String type = "";
        int eventType = scanner.nextInt();
        scanner.nextLine(); 
        if (eventType == 1) type = "Concert";
        else if (eventType == 2) type = "Theater";
        else if (eventType == 3) type = "Conference";
        else {
            System.out.println("Invalid type. Defaulting to Concert.");
            type = "Concert";
        }

        currentEvent = new Event(name, date, time, location, type);
        currentEvent.saveToFile("event.txt");
        System.out.println("Event created and saved successfully!");
    }

    private static void addTickets() {
        if (currentEvent == null) {
            System.out.println("No event created yet!");
            return;
        }

        System.out.print("Enter ticket type (VIP, General, Palcos, Preferencial): ");
        String ticketType = scanner.nextLine();

        System.out.print("Enter ticket quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        currentEvent.addTicket(ticketType, quantity, price);
        currentEvent.saveToFile("event.txt");

        System.out.println("Ticket added and saved!");
    }

    private static void sellTickets() {
        if (currentEvent == null) {
            System.out.println("No event created yet!");
            return;
        }

        System.out.print("Enter ticket type to sell: ");
        String ticketType = scanner.nextLine();

        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (currentEvent.sellTickets(ticketType, quantity)) {
            currentEvent.saveToFile("event.txt");
            System.out.println("Tickets sold and updated successfully!");
        } else {
            System.out.println("Cannot sell more tickets than available!");
        }
    }

    private static void viewEventDetails() {
        if (currentEvent == null) {
            System.out.println("No event created yet!");
            return;
        }

        System.out.println("Event details:\n" + currentEvent);
    }

    private static void saveSummaryToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            String summary = "EVENT SUMMARY:\n" + currentEvent.toString();
            writer.write(summary);
            System.out.println("\n" + summary); 
            System.out.println("Summary saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing summary to file.");
        }
    }
}
