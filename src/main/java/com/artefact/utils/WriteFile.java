package com.artefact.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * WriteFile class handles saving user data to a local file.
 * It manages the formatting and writing of user information, menu history,
 * and TDEE calculations into a formatted text file.
 */
public class WriteFile {

    // ====== Class Fields ======

    private String firstName;                // User's first name
    private String lastName;                 // User's last name
    private String placeOfWork;              // User's workplace
    private int yearsOfWork;                 // Years at current workplace
    private ArrayList<Integer> menuHistory;  // History of menu selections
    private double tdeeResult;               // Calculated TDEE result

    // ====== Data Management Methods ======

    /**
     * Sets user data and program results for file writing
     * @param firstName User's first name
     * @param lastName User's last name
     * @param placeOfWork User's workplace
     * @param yearsOfWork Years at current workplace
     * @param menuHistory List of menu selections made
     * @param tdeeResult Calculated TDEE value
     */
    public void setUserData(String firstName, String lastName, String placeOfWork,
                            int yearsOfWork, ArrayList<Integer> menuHistory, double tdeeResult) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfWork = placeOfWork;
        this.yearsOfWork = yearsOfWork;
        this.menuHistory = menuHistory;
        this.tdeeResult = tdeeResult;
    }

    // ====== File Operations ======

    /**
     * Writes all collected user information to a text file
     * File is named using user's last name and current date
     * Includes personal information, menu history, and TDEE results
     */
    public void writeToFile() {
        try {
            LocalDate date = LocalDate.now();
            String dateAndTime = timeFormatter();

            // Use try-with-resources to ensure proper file handling
            try (FileWriter writer = new FileWriter(lastName + date)) {
                // Write user information section
                writer.write("Student information --\n\n");
                writer.write("First name: " + firstName + "\n");
                writer.write("Last name: " + lastName + "\n");
                writer.write("Place of work: " + placeOfWork + "\n");
                writer.write("Years worked in current role: " + yearsOfWork + "\n\n");

                // Write menu history section
                writer.write("Selected menu options: \n\n");
                for (Integer option : menuHistory) {
                    writer.write(option + "\n");
                }
                writer.write("\n");

                // Write TDEE results
                writer.write("TDEE result: " + tdeeResult + " calories/day\n\n");

                // Write file creation timestamp
                writer.write("File created @ " + dateAndTime);
            }
            System.out.println("Successfully wrote to file.");

        } catch (IOException e) {
            System.out.println("An error occurred: ");
            System.out.println(e);
        }
    }

    // ====== Utility Methods ======

    /**
     * Formats the current date and time into a readable string
     * Uses format: dd-MM-yyyy HH:mm:ss
     * @return Formatted date and time string
     */
    public String timeFormatter() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }
}
