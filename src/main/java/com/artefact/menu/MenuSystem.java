package com.artefact.menu;

import com.artefact.pokemon.battle.PokemonBattler;
import com.artefact.utils.WriteFile;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * MenuSystem class handles the main programme loop and menu interactions.
 * It manages user data, menu options, and provides a menu loop.
 * including TDEE calculation and Pokemon battles.
 */
public class MenuSystem {

    // ====== Class Fields ======

    private String firstName;
    private String lastName;
    private String placeOfWork;
    private int yearsOfWork;
    private final TDEE tdee = new TDEE();

    // ====== Data Management Methods ======

    /**
     * Sets user data collected from the greeting page
     * @param firstName User's first name
     * @param lastName User's last name
     * @param placeOfWork User's workplace
     * @param yearsOfWork Years at current workplace
     */
    public void setUserData(String firstName, String lastName, String placeOfWork, int yearsOfWork) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfWork = placeOfWork;
        this.yearsOfWork = yearsOfWork;
    }

    // ====== Menu System Methods ======

    /**
     * Main menu loop that displays options and processes user input
     * Continues until user chooses to exit
     */
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        boolean userContinue = true;

        // Menu options stored in array for easy update
        String[] menuOptions = {
                "Option 1: See how many years you have left until retirement",
                "Option 2: Remember where you work",
                "Option 3: See your full name",
                "Option 4: Hear some personalised words of encouragement",
                "Option 5: TDEE Calculator",
                "Option 6: Pokemon Battle"
        };

        // Track menu selections for history
        ArrayList<Integer> menuHistory = new ArrayList<>();

        while (userContinue) {
            // Display menu header and options
            System.out.printf("""

                In this programme you have %d choices:
                -------------------------------------
                """, menuOptions.length);

            for (String option : menuOptions) {
                System.out.println(option);
            }
            System.out.printf("\nPlease pick (1-%d):", menuOptions.length);

            // Get and process user selection
            int menuOption = sc.nextInt();
            sc.nextLine();  // Consume newline
            menuHistory.add(menuOption);

            // Process menu selection
            switch (menuOption) {
                case 1 -> {
                    System.out.printf("Great job %s, you have selected the first option.\n", firstName);
                    System.out.printf("Most people work for 35 years, so you have %s to go.\n",
                            (35 - yearsOfWork));
                }
                case 2 -> {
                    System.out.printf("Great job %s, you have selected the second option.\n", firstName);
                    System.out.printf("You work at %s\n", placeOfWork);
                }
                case 3 -> {
                    System.out.printf("Great job %s, you have selected the third option.\n", firstName);
                    System.out.printf("Your full name is: %s %s\n", firstName, lastName);
                }
                case 4 -> {
                    System.out.printf("Great job %s, you have selected the fourth option.\n", firstName);
                    System.out.printf("You're doing really well %s. You're living up to the %s name!\n",
                            firstName, lastName);
                }
                case 5 -> tdee.showMenu();
                case 6 -> startPokemonBattle();
                default -> {
                    System.out.println("Invalid menu option.\n");
                    continue;
                }
            }

            // Check if user wants to continue
            userContinue = checkContinue(sc, menuHistory);
        }
    }

    /**
     * Checks if user wants to continue using the programme
     * Handles saving data on exit
     * @param sc Scanner for user input
     * @param menuHistory List of user's menu selections
     * @return boolean indicating whether to continue
     */
    public boolean checkContinue(Scanner sc, ArrayList<Integer> menuHistory) {
        System.out.println("Would you like to continue? (Yes/No)");
        System.out.println("(Your information will be saved on exit.)");

        String userInput = sc.nextLine().toLowerCase();

        if (userInput.equals("no")) {
            WriteFile writeFile = new WriteFile();
            writeFile.setUserData(firstName, lastName, placeOfWork, yearsOfWork,
                    menuHistory, tdee.getTdeeResult());
            writeFile.writeToFile();
            System.out.println("Thank you for using the programme! Have a great day!");
            return false;
        } else if (!userInput.equals("yes")) {
            System.out.println("Invalid input. Please enter 'Yes' or 'No'.\n");
            return checkContinue(sc, menuHistory);
        }
        return true;
    }

    public void startPokemonBattle() {
        System.out.println("Welcome to the Pokemon Battle!");
        PokemonBattler battler = new PokemonBattler();
        battler.startBattle();
    }
}