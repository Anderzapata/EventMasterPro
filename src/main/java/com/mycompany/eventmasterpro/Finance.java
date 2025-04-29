
package com.mycompany.eventmasterpro;

import java.util.ArrayList;
import java.util.List;

public class Finance {
    private double budget;
    private List<Double> incomes;
    private List<Double> expenses;

    public Finance(double budget) {
        this.budget = budget;
        this.incomes = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }

    public void addIncome(double amount) {
        incomes.add(amount);
    }

    public void addExpense(double amount) {
        expenses.add(amount);
    }

    public double getBalance() {
        double totalIncome = incomes.stream().mapToDouble(Double::doubleValue).sum();
        double totalExpense = expenses.stream().mapToDouble(Double::doubleValue).sum();
        return budget + totalIncome - totalExpense;
    }

    @Override
    public String toString() {
        return "Budget: $" + budget + ", Total Incomes: $" + incomes.stream().mapToDouble(Double::doubleValue).sum()
                + ", Total Expenses: $" + expenses.stream().mapToDouble(Double::doubleValue).sum()
                + ", Current Balance: $" + getBalance();
    }
}
