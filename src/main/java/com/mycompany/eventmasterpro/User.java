
package com.mycompany.eventmasterpro;


import java.io.*;

public class User {
    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean authenticate(String inputPassword) {
        return password.equals(inputPassword);
    }

    public String toDataString() {
        return username + ";" + password + ";" + role;
    }

    public static User fromDataString(String data) {
        String[] parts = data.split(";");
        if (parts.length == 3) {
            return new User(parts[0], parts[1], parts[2]);
        }
        return null;
    }

    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(toDataString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }
}