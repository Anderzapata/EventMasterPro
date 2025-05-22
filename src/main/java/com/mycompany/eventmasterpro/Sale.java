
package com.mycompany.eventmasterpro;

public class Sale {
    private String ticketType;
    private int quantitySold;

    public Sale(String ticketType, int quantitySold) {
        this.ticketType = ticketType;
        this.quantitySold = quantitySold;
    }

    public String getTicketType() {
        return ticketType;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void addQuantity(int amount) {
        this.quantitySold += amount;
    }

    @Override
    public String toString() {
        return "Ticket Type: " + ticketType + ", Quantity Sold: " + quantitySold;
    }
}
