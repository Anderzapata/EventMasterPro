
package com.mycompany.eventmasterpro;

import java.util.*;

public class EventMasterPro {

     private static Scanner scanner = new Scanner(System.in);
    private static List<Location> locations = new ArrayList<>();
    private static List<Event> events = new ArrayList<>();
    private static List<Artist> artists = new ArrayList<>();
    private static List<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {
        int option;
        do {
            showMainMenu();
            option = readIntegerInput("Choose an option: ");
            switch (option) {
                case 1 -> manageLocations();
                case 2 -> manageEvents();
                case 3 -> manageArtists();
                case 4 -> manageTickets();
                case 5 -> System.out.println("Exiting EventMaster Pro. Goodbye!");
                default -> System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);
    }

    private static void showMainMenu() {
        System.out.println("\n=== Welcome to EventMaster Pro (Medellin) ===");
        System.out.println("1. Manage Locations");
        System.out.println("2. Manage Events");
        System.out.println("3. Manage Artists");
        System.out.println("4. Manage Tickets");
        System.out.println("5. Exit");
    }

    private static void manageLocations() {
        System.out.println("\n=== Manage Locations ===");
        String name = readStringInput("Enter location name: ");
        int capacity = readIntegerInput("Enter capacity: ");
        String technicalDetails = readStringInput("Enter technical details: ");
        boolean isAvailable = true; // Default available when created
        locations.add(new Location(name, capacity, technicalDetails, isAvailable));
        System.out.println("Location added successfully.");
    }

    private static void manageEvents() {
        System.out.println("\n=== Manage Events ===");
        if (locations.isEmpty()) {
            System.out.println("No locations available. Please add a location first.");
            return;
        }

        String name = readStringInput("Enter event name: ");
        String date = readStringInput("Enter event date (YYYY-MM-DD): ");
        String time = readStringInput("Enter event time (HH:MM): ");

        System.out.println("Available locations:");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println((i + 1) + ". " + locations.get(i).getName());
        }
        int locationIndex = readIntegerInput("Select a location by number: ") - 1;
        if (locationIndex < 0 || locationIndex >= locations.size()) {
            System.out.println("Invalid location selected.");
            return;
        }
        Location selectedLocation = locations.get(locationIndex);

        System.out.println("Event Types: ");
        for (EventType type : EventType.values()) {
            System.out.println("- " + type);
        }
        String eventTypeInput = readStringInput("Enter event type (CONCERT, THEATER, CONFERENCE): ").toUpperCase();
        EventType eventType;
        try {
            eventType = EventType.valueOf(eventTypeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid event type.");
            return;
        }

        events.add(new Event(name, date, time, selectedLocation, eventType));
        System.out.println("Event created successfully.");
    }

    private static void manageArtists() {
        System.out.println("\n=== Manage Artists ===");
        String name = readStringInput("Enter artist name: ");
        String contactInfo = readStringInput("Enter contact information: ");
        String technicalRequirements = readStringInput("Enter technical requirements: ");
        artists.add(new Artist(name, contactInfo, technicalRequirements));
        System.out.println("Artist added successfully.");
    }

    private static void manageTickets() {
        System.out.println("\n=== Manage Tickets ===");
        if (events.isEmpty()) {
            System.out.println("No events available. Please create an event first.");
            return;
        }

        System.out.println("Available events:");
        for (int i = 0; i < events.size(); i++) {
            System.out.println((i + 1) + ". " + events.get(i).getName() + " (" + events.get(i).getEventType() + ")");
        }
        int eventIndex = readIntegerInput("Select an event by number: ") - 1;

        if (eventIndex < 0 || eventIndex >= events.size()) {
            System.out.println("Invalid event selected.");
            return;
        }

        Event selectedEvent = events.get(eventIndex);

        double price = readDoubleInput("Enter ticket price: ");
        int quantity = readIntegerInput("Enter number of tickets to create: ");

        for (int i = 0; i < quantity; i++) {
            Ticket ticket = new Ticket(selectedEvent, price);
            tickets.add(ticket);
        }

        System.out.println(quantity + " tickets created for event '" + selectedEvent.getName() + "' successfully!");
    }

 
    private static int readIntegerInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double readDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.print("Please enter a valid decimal number: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    private static String readStringInput(String prompt) {
        System.out.print(prompt);
        scanner.nextLine(); 
        return scanner.nextLine();
    }
}