
package com.mycompany.eventmasterpro;

import java.util.Scanner;

public class FinancialManager {
    private double budget = 0.0;
    private double totalIncome = 0.0;
    private double totalExpenses = 0.0;

    
    public void manageFinances(Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Financial Management ---");
            System.out.println("1. Set Event Budget");
            System.out.println("2. Add Expense");
            System.out.println("3. View Financial Summary");
            System.out.println("4. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter event budget: $");
                    budget = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Budget set to $" + budget);
                    break;
                case 2:
                    System.out.print("Enter expense description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter amount: $");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    addExpense(amount);
                    System.out.println("Expense added: " + description + " - $" + amount);
                    break;
                case 3:
                    showSummary();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

  
    public void addIncome(double amount) {
        totalIncome += amount;
    }


    public void addExpense(double amount) {
        totalExpenses += amount;
    }

  
    public void showSummary() {
        double profit = totalIncome - totalExpenses;
        double balance = budget - totalExpenses;

        System.out.println("\n--- Financial Summary ---");
        System.out.println("Budget: $" + budget);
        System.out.println("Total Income (Tickets): $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpenses);
        System.out.println("Remaining Budget: $" + balance);
        System.out.println("Profit/Loss: $" + profit);
    }
}