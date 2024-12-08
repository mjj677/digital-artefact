package com.artefact.menu;

import java.util.Scanner;

/**
 * TDEE class handles the calculation of Total Daily Energy Expenditure.
 * It manages user input collection, validation, and TDEE calculation.
 */
public class TDEE {

    // ====== Class Fields ======
    public int age;                // User's age
    public double weight;          // User's weight
    public int height;             // User's height
    public int activityLevel;      // User's activity level
    public double activityFactor;  // Activity factor based on level
    public double tdeeResult;      // Calculated TDEE result
    public final Scanner sc = new Scanner(System.in);

    // ====== Main Method ======

    /**
     * Entry point for TDEE calculator
     */
    public static void main(String[] args) {
        TDEE tdee = new TDEE();
        tdee.showMenu();
    }

    // ====== Menu Methods ======

    /**
     * Displays and handles the main calculator menu
     * Loops until user chooses to exit
     */
    public void showMenu() {
        int choice;

        do {
            System.out.println("\nTDEE Calculator Menu:");
            System.out.println("1. Enter your details and calculate TDEE");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> {
                    collectUserData();
                    setActivityLevel();
                    tdeeResult = calculateExpenditure();
                    System.out.println("Your TDEE is " + tdeeResult + " calories/day.");
                }
                case 2 -> System.out.println("Exiting the program...");
                default -> System.out.println("Invalid choice. Please choose a valid option.");
            }
        } while (choice != 2);
    }

    // ====== Data Collection Methods ======

    /**
     * Collects all required user information with input validation
     * Includes age, weight, height, and activity level
     */
    public void collectUserData() {
        System.out.println("Welcome to the TDEE Calculator!");

        age = promptForInt("Enter your age: ", 1, 120);
        weight = promptForDouble("Enter your weight (kg): ", 0.1, 500);
        height = promptForInt("Enter your height (cm): ", 30, 300);
        activityLevel = promptForInt("""
            Enter your activity level:

            (1) Sedentary,
            (2) Lightly Active,
            (3) Moderately Active,
            (4) Very Active,
            (5) Super Active
            """, 1, 5);
    }

    // ====== Input Validation Methods ======

    /**
     * Validates and collects integer input within specified range
     * @param prompt The message to display to the user
     * @param min Minimum acceptable value
     * @param max Maximum acceptable value
     * @return Valid integer within the specified range
     */
    public int promptForInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= min && value <= max) {
                    sc.nextLine();  // Consume newline
                    return value;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine();  // Consume invalid input
            }
        }
    }

    /**
     * Validates and collects decimal input within specified range
     * @param prompt The message to display to the user
     * @param min Minimum acceptable value
     * @param max Maximum acceptable value
     * @return Valid decimal number within the specified range
     */
    public double promptForDouble(String prompt, double min, double max) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                if (value >= min && value <= max) {
                    sc.nextLine();  // Consume newline
                    return value;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();  // Consume invalid input
            }
        }
    }

    /**
     * Validates and collects menu choice input
     * @return Valid integer for menu selection
     */
    public int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid choice. Please enter a valid number.");
            sc.nextLine();  // Consume invalid input
        }
        return sc.nextInt();
    }

    // ====== Calculation Methods ======

    /**
     * Sets activity factor based on user's activity level
     */
    public void setActivityLevel() {
        switch (activityLevel) {
            case 1 -> activityFactor = 1.2;    // Sedentary
            case 2 -> activityFactor = 1.375;  // Lightly Active
            case 3 -> activityFactor = 1.55;   // Moderately Active
            case 4 -> activityFactor = 1.725;  // Very Active
            case 5 -> activityFactor = 1.9;    // Super Active
            default -> System.out.println("Invalid activity level.\n");
        }
    }

    /**
     * Calculates TDEE.
     * Formula: BMR = 10 × weight(kg) + 6.25 × height(cm) - 5 × age + 5
     * TDEE = BMR × activity factor
     * @return Calculated TDEE value
     */
    public double calculateExpenditure() {
        double BMR = 10 * weight + 6.25 * height - 5 * age + 5;
        System.out.println("Your BMR (Base Metabolic Rate) is " + BMR + " calories per day.");
        tdeeResult = BMR * activityFactor;
        return tdeeResult;
    }

    /**
     * Getter method for accessing the calculated TDEE result
     * @return The calculated TDEE value
     */
    public double getTdeeResult() {
        return tdeeResult;
    }
}

