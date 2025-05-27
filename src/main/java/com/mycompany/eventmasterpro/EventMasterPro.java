
package com.mycompany.eventmasterpro;

import java.io.*;
import java.util.*;

public class EventMasterPro {

    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static Event currentEvent;
    private static final List<User> users = new ArrayList<>();
    private static final List<Artist> artists = new ArrayList<>();
    private static final FinancialManager financialManager = new FinancialManager(); 
    private static final AccessManager accessManager = new AccessManager(); 

    public static void main(String[] args) {
        System.out.println("Welcome to EventMaster Pro!");

        loadUsersFromFile("user.txt");
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
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Create Event");
            System.out.println("2. Manage Artists");
            System.out.println("3. Add Tickets");
            System.out.println("4. Sell Tickets");
            System.out.println("5. View Event Details");
            if (currentUser.getRole().equalsIgnoreCase("admin")) {
                System.out.println("6. Financial Management");
                System.out.println("7. Access and Attendance Management");
            }
            System.out.println("8. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createEvent();
                    break;
                case 2:
                    manageArtists();
                    break;
                case 3:
                    addTickets();
                    break;
                case 4:
                    sellTickets();
                    break;
                case 5:
                    viewEventDetails();
                    break;
                case 6:
                    if (currentUser.getRole().equalsIgnoreCase("admin")) {
                        financialManager.showFinancialReport(currentEvent);
                        saveFinancialReportToFile("financial_report.txt");
                    } else {
                        System.out.println("Access denied: Only admins can manage finances.");
                    }
                    break;
                case 7:
                    if (currentUser.getRole().equalsIgnoreCase("admin")) {
                        accessManager.manageAccess(scanner, currentEvent);
                    } else {
                        System.out.println("Access denied: Only admins can manage access and attendance.");
                    }
                    break;
                case 8:
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
            User paula = new User("Paula", "Admin123*", "user");
            users.add(admin);
            users.add(paula);
            admin.saveToFile("user.txt");
            paula.saveToFile("user.txt");
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

        System.out.print("Enter event name: ");
        String name = scanner.nextLine();
        System.out.print("Enter event date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter event time (HH:MM): ");
        String time = scanner.nextLine();
        System.out.print("Enter event location: ");
        String location = scanner.nextLine();

        currentEvent = new Event(name, date, time, location, type);
        currentEvent.saveToFile("event.txt");
        System.out.println("Event created and saved successfully!");
    }

    private static void manageArtists() {
        boolean back = false;
        while (!back) {
            System.out.println("\nManage Artists:");
            System.out.println("1. Add Artist");
            System.out.println("2. List Artists");
            System.out.println("3. Back to Main Menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter artist name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter nationality: ");
                    String nationality = scanner.nextLine();
                    Artist artist = new Artist(name, genre, nationality);
                    artists.add(artist);
                    artist.saveToFile("artist.txt");
                    System.out.println("Artist added and saved successfully!");
                    break;
                case 2:
                    if (artists.isEmpty()) {
                        System.out.println("No artists registered.");
                    } else {
                        System.out.println("Registered Artists:");
                        for (Artist a : artists) {
                            System.out.println(a);
                        }
                    }
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTickets() {
        if (currentEvent == null) {
            System.out.println("No event created yet!");
            return;
        }
        System.out.println("Select Ticket type: (1) VIP (2) GENERAL (3) PALCO (4) PREFERENCIAL");
        String type = "";
        int TicketType = scanner.nextInt();
        scanner.nextLine();
        if (TicketType == 1) type = "VIP";
        else if (TicketType == 2) type = "GENERAL";
        else if (TicketType == 3) type = "PALCO";
        else if (TicketType == 4) type = "PREFERENCIAL";
        else {
            System.out.println("Invalid type. Defaulting to GENERAL.");
            type = "GENERAL";
        }

        System.out.print("Enter ticket quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        currentEvent.addTickets(type, quantity, price);
        currentEvent.saveToFile("ticket.txt");

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

    private static void saveFinancialReportToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            String report = financialManager.generateFinancialReport(currentEvent);
            writer.write(report);
            System.out.println("Financial report saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing financial report to file.");
        }
    }
}

