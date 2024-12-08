package com.artefact.pokemon.battle;

import com.artefact.utils.Constants;
import com.artefact.pokemon.Pokemon;
import com.artefact.pokemon.types.FirePokemon;
import com.artefact.pokemon.types.WaterPokemon;
import com.artefact.pokemon.types.GrassPokemon;
import com.artefact.pokemon.Move;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * PokemonBattler class handles the battle logic and user interactions for Pokemon battles.
 * It manages the battle flow, move selection, and battle history recording.
 */
public class PokemonBattler {

    // ====== Class Fields ======
    private final Scanner scanner = new Scanner(System.in);
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> battleCommentary = new ArrayList<>();  // List of battle comments

    // ====== Main Battle Flow Methods ======

    /**
     * Initiates a new battle sequence between player and computer
     */
    public void startBattle() {
        Pokemon playerPokemon = choosePokemon();
        Pokemon computerPokemon = generateOpponent(playerPokemon);
        battle(playerPokemon, computerPokemon);
    }

    /**
     * Main battle loop handling turns and combat
     */
    public void battle(Pokemon player, Pokemon computer) {
        System.out.println("\nBattle Start!");
        addCommentary("Battle started between " + player.name + " and " + computer.name);

        boolean surrendered = false;

        while (player.isAlive() && computer.isAlive() && !surrendered) {
            displayBattleStatus(player, computer);
            displayBattleHistory();

            surrendered = !handlePlayerTurn(player, computer);

            if (surrendered || !computer.isAlive()) break;

            handleComputerTurn(computer, player);
        }

        if (!surrendered) {
            announceWinner(player, computer);
        }
    }

    // ====== Pokemon Selection Methods ======

    /**
     * Handles the player's Pokemon selection
     */
    private Pokemon choosePokemon() {
        System.out.println("Choose your Pokemon:");
        System.out.println("1. Charizard (Fire Type)");
        System.out.println("2. Blastoise (Water Type)");
        System.out.println("3. Venusaur (Grass Type)");

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                return switch (choice) {
                    case 1 -> FirePokemon.create("Charizard", 100, 20);
                    case 2 -> WaterPokemon.create("Blastoise", 100, 20);
                    case 3 -> GrassPokemon.create("Venusaur", 140, 10);
                    default -> {
                        System.out.println("Invalid choice. Please choose 1-3:");
                        yield choosePokemon();
                    }
                };
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1-3):");
            }
        }
    }

    /**
     * Generates a random opponent Pokemon different from player's choice
     */
    public Pokemon generateOpponent(Pokemon playerPokemon) {
        Random rand = new Random();
        Pokemon opponent;

        do {
            int choice = rand.nextInt(3) + 1;
            opponent = switch (choice) {
                case 2 -> WaterPokemon.create("Blastoise", 100, 20);
                case 3 -> GrassPokemon.create("Venusaur", 100, 20);
                default -> FirePokemon.create("Charizard", 100, 20);
            };
        } while (opponent.getClass() == playerPokemon.getClass());

        System.out.println("\nYour opponent is " + opponent.name + "!");
        return opponent;
    }

    // ====== Turn Handling Methods ======

    /**
     * Handles the player's turn including move selection and action execution
     * @return boolean indicating whether to continue the battle (false if surrendered)
     */
    private boolean handlePlayerTurn(Pokemon player, Pokemon computer) {
        while (true) {  // Keep looping until a valid move is made
            System.out.println("\nYour turn! Choose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Use Potion (" + player.potions + " remaining)");
            System.out.println("3. Surrender");

            try {
                String input = scanner.nextLine().trim();
                int choice;

                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (1-3).");
                    continue;  // Restart the loop
                }

                switch (choice) {
                    case 1 -> {
                        executePlayerMove(player, computer);
                        return true;
                    }
                    case 2 -> {
                        if (player.potions > 0) {
                            player.heal();
                            addCommentary(player.name + " used a potion!");
                        } else {
                            player.heal();
                            addCommentary("No potions remaining!");
                        }
                        return true;
                    }
                    case 3 -> {
                        System.out.println("You surrendered the battle!");
                        System.out.println("Returning to menu... \n");
                        addCommentary(player.name + " surrendered the battle!");
                        saveBattleResults(computer, player);
                        return false;  // Indicate surrender
                    }
                    default -> {
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                        continue;  // Restart the loop
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    /**
     * Handles the computer's turn including move selection and healing decisions
     */
    private void handleComputerTurn(Pokemon computer, Pokemon player) {
        System.out.println("\nOpponent's turn!");

        if (computer.getHealth() < 40 && computer.hasHeals() && Math.random() < 0.7) {
            computer.computerHeal();
            addCommentary(computer.name + " used a healing move!");
        } else {
            executeComputerMove(computer, player);
        }
    }

    // ====== Combat Mechanics Methods ======

    /**
     * Executes player's selected move
     */
    private void executePlayerMove(Pokemon player, Pokemon computer) {
        displayMovesList(player);
        System.out.println("Choose your move (1-" + player.moves.size() + "):");

        try {
            int moveChoice = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (moveChoice >= 0 && moveChoice < player.moves.size()) {
                executeMove(player, computer, player.moves.get(moveChoice));
            } else {
                System.out.println("Invalid move choice. Turn skipped!");
                addCommentary(player.name + " stumbled and missed their turn!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Turn skipped!");
        }
    }

    /**
     * Executes computer's randomly selected move
     */
    private void executeComputerMove(Pokemon computer, Pokemon player) {
        if (computer.moves == null || computer.moves.isEmpty()) {
            System.out.println("ERROR: No moves available for " + computer.name);
            return;
        }

        try {
            Move computerMove = computer.moves.get(new Random().nextInt(computer.moves.size()));
            executeMove(computer, player, computerMove);
        } catch (Exception e) {
            System.out.println("ERROR: Failed to execute computer move: " + e.getMessage());
        }
    }

    /**
     * Executes a move between attacker and defender
     */
    private void executeMove(Pokemon attacker, Pokemon defender, Move move) {
        if (move.attemptHit()) {
            double typeMultiplier = getTypeMultiplier(attacker.type, defender.type);
            int damage = (int)(move.basePower() * typeMultiplier);
            defender.takeDamage(damage);

            String effectiveness = typeMultiplier > 1.0 ? " It's super effective!" : "";
            addCommentary(attacker.name + " used " + move.name() +
                    " dealing " + damage + " damage!" + effectiveness);
        } else {
            System.out.println("The attack missed!");
            addCommentary(attacker.name + "'s " + move.name() + " missed!");
        }
    }

    // ====== Display Methods ======

    /**
     * Displays current battle status including health bars
     */
    private void displayBattleStatus(Pokemon player, Pokemon computer) {
        System.out.println("\n=== Battle Status ===");
        System.out.println("Your " + player.name + " - " + player.getHealthBar());
        System.out.println("Opponent's " + computer.name + " - " + computer.getHealthBar());
    }

    /**
     * Displays available moves for a Pokemon
     */
    private void displayMovesList(Pokemon pokemon) {
        System.out.println("\nAvailable moves:");
        for (int i = 0; i < pokemon.moves.size(); i++) {
            Move move = pokemon.moves.get(i);
            System.out.printf("%d. %s (Power: %d, Accuracy: %.0f%%)\n",
                    i + 1, move.name(), move.basePower(),
                    move.accuracy() * 100);
        }
    }

    /**
     * Displays recent battle events
     */
    private void displayBattleHistory() {
        System.out.println("\n=== Recent Battle Events ===");
        for (String comment : battleCommentary) {
            System.out.println("» " + comment);
        }
        System.out.println("========================");
    }

    // ====== Utility Methods ======

    /**
     * Calculates type effectiveness multiplier
     */
    private double getTypeMultiplier(String attackerType, String defenderType) {
        if (attackerType.equals("Water") && defenderType.equals("Fire") ||
                attackerType.equals("Fire") && defenderType.equals("Grass") ||
                attackerType.equals("Grass") && defenderType.equals("Water")) {
            return Constants.TYPE_ADVANTAGE_MULTIPLIER;
        }
        return 1.0;
    }

    /**
     * Adds commentary to battle history
     */
    private void addCommentary(String comment) {
        battleCommentary.add(comment);
        if (battleCommentary.size() > Constants.MAX_BATTLE_HISTORY) {
            battleCommentary.remove(0);
        }
    }

    /**
     * Announces battle winner and saves results
     */
    private void announceWinner(Pokemon player, Pokemon computer) {
        System.out.println("\nBattle End!");
        Pokemon winner = player.isAlive() ? player : computer;
        Pokemon loser = player.isAlive() ? computer : player;

        if (player.isAlive()) {
            System.out.println("Congratulations! Your " + player.name + " wins the battle!");
        } else {
            System.out.println("Your Pokemon was defeated! Opponent's " + computer.name + " wins!");
        }

        saveBattleResults(winner, loser);
    }

    /**
     * Saves battle results to file
     */
    private void saveBattleResults(Pokemon winner, Pokemon loser) {
        // Create the full path using Constants
        String filename = String.format("%s/battle_history%s",
                Constants.OUTPUT_DIR,
                Constants.FILE_EXTENSION);

        try (FileWriter writer = new FileWriter(filename, true)) {  // true for append mode
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);

            writer.write("\nBattle @ " + now.format(formatter) + "\n");
            writer.write("Winner: " + winner.name + " (" + winner.type + ")\n");
            writer.write("Loser: " + loser.name + " (" + loser.type + ")\n");
            writer.write("Battle Commentary:\n");
            for (String comment : battleCommentary) {
                writer.write(comment + "\n");
            }
            writer.write("------------------------\n");

            System.out.println("Battle history saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save battle history to " + filename);
            System.err.println("Error details: " + e.getMessage());
        }
    }
}