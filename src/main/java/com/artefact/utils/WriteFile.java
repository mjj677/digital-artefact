package com.artefact.utils;

import java.io.File;
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

    // ====== Constructor ======
    public WriteFile() {
        // Create output directory if it doesn't exist
        createOutputDirectory();
    }

    // ====== Data Management Methods ======
    /**
     * Sets user data and program results for file writing
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
     * Creates the output directory if it doesn't exist
     */
    private void createOutputDirectory() {
        File directory = new File(Constants.OUTPUT_DIR);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("Failed to create output directory: " + Constants.OUTPUT_DIR);
            }
        }
    }

    /**
     * Generates a safe filename from the user's last name and current date
     */
    private String generateFilename() {
        LocalDate date = LocalDate.now();
        String safeLastName = lastName.replaceAll("[^a-zA-Z0-9.-]", "_");
        return String.format("%s/%s_%s%s",
                Constants.OUTPUT_DIR,
                safeLastName,
                date.format(DateTimeFormatter.ofPattern(Constants.FILE_DATE_FORMAT)),
                Constants.FILE_EXTENSION);
    }

    /**
     * Writes all collected user information to a text file.
     * File is named using user's last name and current date
     * Includes personal information, menu history, and TDEE results
     */
    public void writeToFile() {
        if (lastName == null || lastName.trim().isEmpty()) {
            System.err.println("Error: Last name is required for file creation");
            return;
        }

        String filename = generateFilename();
        String dateAndTime = timeFormatter();

        try (FileWriter writer = new FileWriter(filename)) {
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

            System.out.println("Successfully wrote to file: " + filename);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + filename);
            System.err.println("Error details: " + e.getMessage());
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);
        return dateTime.format(formatter);
    }
}