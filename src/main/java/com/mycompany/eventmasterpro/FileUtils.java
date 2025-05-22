/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmasterpro;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileUtils {
    public static void showFileIfExists(String filePath) {
        if (Files.exists(Paths.get(filePath))) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                System.out.println("=== File Content: " + filePath + " ===");
                for (String line : lines) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Failed to read file: " + e.getMessage());
            }
        } else {
            System.out.println("File '" + filePath + "' does not exist.");
        }
    }
}