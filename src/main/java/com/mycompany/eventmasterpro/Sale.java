
package com.mycompany.eventmasterpro;

public class Sale {
    private Ticket ticket;
    private int quantity;

    public Sale(Ticket ticket, int quantity) {
        this.ticket = ticket;
        this.quantity = quantity;
    }

    public double totalSale() {
        return ticket.getPrice() * quantity;
    }

    public Ticket getTicket() { return ticket; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "Sold " + quantity + " tickets of " + ticket.getType() + " - Total: $" + totalSale();
    }
}
