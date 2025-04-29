
package com.mycompany.eventmasterpro;

public class Ticket {
    private Event event;
    private double price;
    private TicketType ticketType;
    private boolean isSold;

    public Ticket(Event event, double price) {
        this.event = event;
        this.price = price;
        this.ticketType = ticketType;
        this.isSold = false;
    }

  public TicketType getTicketType() {
        return ticketType;
    }
    public Event getEvent() { 
        return event; 
    }
    public void setEvent(Event event) { 
        this.event = event; 
    }

    public double getPrice() { 
        return price; 
    }
    public void setPrice(double price) { 
        this.price = price; 
    }

    public boolean isSold() { 
        return isSold; 
    }
    public void setSold(boolean sold) { 
        isSold = sold; 
    }

    @Override
    public String toString() {
        return "Ticket for event: " + event.getName() + ", Price: " + price + ", Sold: " + isSold;
    }

    String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}