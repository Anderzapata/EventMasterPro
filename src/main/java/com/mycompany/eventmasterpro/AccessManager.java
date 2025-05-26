
package com.mycompany.eventmasterpro;

import java.util.*;

public class AccessManager {
    private final Set<String> validatedEntries = new HashSet<>();
    private final Map<String, Boolean> attendance = new HashMap<>();

   
    public void manageAccess(Scanner scanner, Event event) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Access & Attendance Management ---");
            System.out.println("1. Validate Entry");
            System.out.println("2. Register Attendance");
            System.out.println("3. View Attendance Statistics");
            System.out.println("4. Back");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    validateEntry(scanner);
                    break;
                case 2:
                    registerAttendance(scanner);
                    break;
                case 3:
                    showAttendanceStats();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

   
    private void validateEntry(Scanner scanner) {
        System.out.print("Enter ticket holder ID (document number): ");
        String id = scanner.nextLine();

        if (validatedEntries.contains(id)) {
            System.out.println("Entry already validated.");
        } else {
            validatedEntries.add(id);
            System.out.println("Entry validated successfully.");
        }
    }

   
    private void registerAttendance(Scanner scanner) {
        System.out.print("Enter attendee ID: ");
        String id = scanner.nextLine();

        if (validatedEntries.contains(id)) {
            attendance.put(id, true);
            System.out.println("Attendance recorded.");
        } else {
            System.out.println("This ID was not validated at entry.");
        }
    }

   
    private void showAttendanceStats() {
        int totalValidated = validatedEntries.size();
        int attended = 0;

        for (boolean wasPresent : attendance.values()) {
            if (wasPresent) attended++;
        }

        System.out.println("\n--- Attendance Statistics ---");
        System.out.println("Validated Entries: " + totalValidated);
        System.out.println("Total Attendance: " + attended);
        System.out.println("Attendance Rate: " +
                (totalValidated == 0 ? 0 : (attended * 100 / totalValidated)) + "%");
    }
}

