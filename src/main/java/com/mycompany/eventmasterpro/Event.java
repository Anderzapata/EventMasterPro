
package com.mycompany.eventmasterpro;

import java.io.*;
import java.util.*;

public class Event {
    private String name;
    private String date;
    private String time;
    private String location;
    private String type;
    private Map<String, Ticket> tickets = new HashMap<>();

    public Event(String name, String date, String time, String location, String type) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.type = type;
    }

 
    public String getName() { 
        return name; 
    }
    public String getDate() { 
        return date; 
    }
    public String getTime() { 
        return time; 
    }
    public String getLocation() { 
        return location;
    }
    public String getType() { 
        return type; 
    }
    public Map<String, Ticket> getTickets() { 
        return tickets; 
    }

   
    public void addTickets(String type, int quantity, double price) {
        if (tickets.containsKey(type)) {
            Ticket existing = tickets.get(type);
            existing.increaseQuantity(quantity);
            existing.setPrice(price);
        } else {
            Ticket newTicket = new Ticket(type, quantity, price);
            tickets.put(type, newTicket);
        }
    }

    public boolean sellTickets(String type, int amount) {
        Ticket ticket = tickets.get(type);
        if (ticket != null && ticket.canSell(amount)) {
            ticket.sell(amount);
            return true;
        }
        return false;
    }

    public String toDataString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(";").append(date).append(";").append(time)
          .append(";").append(location).append(";").append(type).append("\n");

        for (Ticket ticket : tickets.values()) {
            sb.append("TICKET:").append(ticket.toDataString()).append("\n");
        }
        return sb.toString();
    }

    public static Event loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String header = reader.readLine();
            if (header == null || !header.contains(";")) return null;

            String[] parts = header.split(";");
            Event event = new Event(parts[0], parts[1], parts[2], parts[3], parts[4]);

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("TICKET:")) {
                    Ticket ticket = Ticket.fromDataString(line.substring(7));
                    if (ticket != null) {
                        event.tickets.put(ticket.getType(), ticket);
                    }
                }
            }
            return event;

        } catch (IOException e) {
            System.out.println("No existing event file found.");
            return null;
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.print(toDataString());
        } catch (IOException e) {
            System.out.println("Error saving event: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n")
          .append("Date: ").append(date).append("\n")
          .append("Time: ").append(time).append("\n")
          .append("Location: ").append(location).append("\n")
          .append("Type: ").append(type).append("\n")
          .append("Tickets:\n");

        for (Ticket ticket : tickets.values()) {
            sb.append(" - ").append(ticket).append("\n");
        }

        return sb.toString();
    }
}

    