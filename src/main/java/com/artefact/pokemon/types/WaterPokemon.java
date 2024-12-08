package com.artefact.pokemon.types;

import com.artefact.pokemon.Pokemon;
import com.artefact.pokemon.Move;
import com.artefact.utils.Constants;

/**
 * WaterPokemon class represents water-type Pokemon.
 * Implements specific water-type moves.
 * Inherits base Pokemon attributes and behaviours.
 */
public class WaterPokemon extends Pokemon {

    // ====== Constructor ======

    /**
     * Creates a new Water-type Pokemon
     * @param name Pokemon's name
     * @param health Starting health points
     * @param attackPower Base attack damage
     */
    public WaterPokemon(String name, int health, int attackPower) {
        super(name, "Water", health, attackPower,
                "It has jet nozzles on its shell. This impressive Pokemon uses these jets " +
                        "to charge toward foes with all the force of a rocket.");
    }

    // ====== Factory Methods ======

    /**
     * Factory method to create new WaterPokemon instances
     * @param name Pokemon's name
     * @param health Starting health points
     * @param attackPower Base attack damage
     * @return New WaterPokemon instance
     */
    public static WaterPokemon create(String name, int health, int attackPower) {
        return (WaterPokemon) Pokemon.createPokemon(
                name,
                "Water",
                health,
                attackPower,
                "It has jet nozzles on its shell. This impressive Pokemon uses these jets " +
                           "to charge toward foes with all the force of a rocket.",
                WaterPokemon.class
        );
    }

    // ====== Battle Methods ======

    /**
     * Executes attack against opponent Pokemon
     * Applies type effectiveness multiplier against Fire-type Pokemon
     * @param opponent Target Pokemon to attack
     */
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(this.name + " uses Water Gun!");

        // Calculate damage with type multiplier
        double multiplier = 1.0;
        if (opponent.type.equals("Fire")) {
            multiplier = 1.1;  // Water is super effective against Fire
            System.out.println("It's super effective!");
        }

        opponent.takeDamage((int)(attackPower * multiplier));
    }

    // ====== Move Management ======

    /**
     * Initialises the WaterPokemon's move set
     * Includes Wave Crash, Water Gun, and Hydro Pump with varying power and accuracy
     */
    @Override
    public void initialiseMoves() {
        moves.add(new Move(
                "Wave Crash",    // High power, low accuracy
                20,
                Constants.MEDIUM_ACCURACY,
                "A powerful wave crashes down!"
        ));
        moves.add(new Move(
                "Water Gun",     // Lower power, high accuracy
                15,
                Constants.HIGH_ACCURACY,
                "A weak but accurate water attack"
        ));
        moves.add(new Move(
                "Hydro Pump",    // Highest power, medium accuracy
                25,
                Constants.LOW_ACCURACY,
                "A devastating but inaccurate attack."
        ));
    }
}
