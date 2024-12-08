package com.artefact;

import com.artefact.menu.MenuSystem;
import com.artefact.utils.Constants;
import java.io.File;
import java.util.Scanner;

/**
 * GreetingPage class handles the initial user interaction and data collection.
 * It manages user input, validation, and creates a personalised summary before
 * launching the main menu system.
 */
public class GreetingPage {

    // ====== Main Method ======

    /**
     * Entry point for the application
     * Collects user information and initialises the menu system
     */
    public static void main(String[] args) {
        // Create output directory first
        createOutputDirectory();

        System.out.println("Hello and welcome to the programme. \nBefore we start, lets collect some data about you.");

        // Using try-with-resources to ensure scanner is properly closed
        try (Scanner scanner = new Scanner(System.in)) {
            // Collect user information
            String firstName = getStringInput("\nWhat is your first name?", scanner);
            String lastName = getStringInput("What is your last name?", scanner);
            String placeOfWork = getStringInput("Where do you work?", scanner);
            int yearsOfWork = getIntInput("How many years have you worked for " + placeOfWork + "?", scanner);

            System.out.println("\nOkay, all information has been gathered.");

            // Generate and display user summary
            String summary = createSummary(firstName, lastName, placeOfWork, yearsOfWork);
            System.out.println("\n" + summary);
            System.out.println("\nNow, let's begin...");

            // Initialise and start menu system
            MenuSystem menuSystem = new MenuSystem();
            menuSystem.setUserData(firstName, lastName, placeOfWork, yearsOfWork);
            menuSystem.displayMenu();

        } catch (Exception e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    /**
     * Creates the output directory if it doesn't exist
     */
    private static void createOutputDirectory() {
        File directory = new File(Constants.OUTPUT_DIR);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("Warning: Failed to create output directory: " + Constants.OUTPUT_DIR);
                System.err.println("Please ensure you have write permissions in this directory.");
            } else {
                System.out.println("Created output directory: " + Constants.OUTPUT_DIR);
            }
        }
    }

    // ====== Input Methods ======

    /**
     * Collects and validates string input from the user
     * @param prompt The message to display to the user
     * @param scanner Scanner instance for input
     * @return Trimmed string input from user
     */
    public static String getStringInput(String prompt, Scanner scanner) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Collects and validates integer input from the user
     * Continues to prompt until valid input is received
     * @param prompt The message to display to the user
     * @param scanner Scanner instance for input
     * @return Valid integer input from user
     */
    public static int getIntInput(String prompt, Scanner scanner) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    // ====== Summary Generation ======

    /**
     * Creates a personalized summary using the collected user information
     * @param firstName User's first name
     * @param lastName User's last name
     * @param placeOfWork User's workplace
     * @param yearsOfWork Number of years at current workplace
     * @return Formatted summary string
     */
    public static String createSummary(String firstName, String lastName,
                                       String placeOfWork, int yearsOfWork) {
        StringBuilder summary = new StringBuilder();
        summary.append("Welcome to the programme ")
                .append(firstName).append(" ").append(lastName)
                .append(".\n")
                .append("Congratulations on working for ")
                .append(placeOfWork)
                .append(" for ").append(yearsOfWork)
                .append(yearsOfWork == 1 ? " year." : " years.");
        return summary.toString();
    }
}
