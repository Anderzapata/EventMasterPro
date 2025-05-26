
package com.mycompany.eventmasterpro;

import java.io.*;
public class Artist {

    static void add(Artist artist) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    static boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    private String name;
    private String genre;
    private String nationality;

    public Artist(String name, String genre, String nationality) {
        this.name = name;
        this.genre = genre;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Genre: " + genre + ", Nationality: " + nationality;
    }

    public String toDataString() {
        return name + ";" + genre + ";" + nationality;
    }

    public static Artist fromDataString(String data) {
        String[] parts = data.split(";");
        if (parts.length == 3) {
            return new Artist(parts[0], parts[1], parts[2]);
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