
package com.mycompany.eventmasterpro;

public class Ticket {
    private String type;
    private int quantity;
    private double price;

    public Ticket(String type, int quantity, double price) {
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void sell(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        }
    }

    public boolean canSell(int amount) {
        return quantity >= amount;
    }

    public String toDataString() {
        return type + ";" + quantity + ";" + price;
    }

    public static Ticket fromDataString(String data) {
        String[] parts = data.split(";");
        if (parts.length == 3) {
            String type = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            double price = Double.parseDouble(parts[2]);
            return new Ticket(type, quantity, price);
        }
        return null;
    }

    @Override
    public String toString() {
        return type + " - Quantity: " + quantity + ", Price: $" + price;
    }
}
