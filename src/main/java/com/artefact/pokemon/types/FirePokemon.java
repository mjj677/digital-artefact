package com.artefact.pokemon.types;

import com.artefact.utils.Constants;
import com.artefact.pokemon.Pokemon;
import com.artefact.pokemon.Move;

/**
 * FirePokemon class represents fire-type Pokemon.
 * Implements specific fire-type moves.
 * Inherits base Pokemon attributes and behaviours.
 */
public class FirePokemon extends Pokemon {

    // ====== Constructor ======

    /**
     * Creates a new Fire-type Pokemon
     * @param name Pokemon's name
     * @param health Starting health points
     * @param attackPower Base attack damage
     */
    public FirePokemon(String name, int health, int attackPower) {
        super(name, "Fire", health, attackPower,
                "A powerful Fire-type Pokemon that breathes scorching flames.");
    }

    // ====== Factory Methods ======

    /**
     * Factory method to create new FirePokemon instances
     * @param name Pokemon's name
     * @param health Starting health points
     * @param attackPower Base attack damage
     * @return New FirePokemon instance
     */
    public static FirePokemon create(String name, int health, int attackPower) {
        return (FirePokemon) Pokemon.createPokemon(
                name,
                "Fire",
                health,
                attackPower,
                "A powerful Fire-type Pokemon that breathes scorching flames.",
                FirePokemon.class
        );
    }

    // ====== Battle Methods ======

    /**
     * Executes attack against opponent Pokemon
     * Applies type effectiveness multiplier against Grass-type Pokemon
     * @param opponent Target Pokemon to attack
     */
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(this.name + " uses Fire Blast!");

        // Calculate damage with type multiplier
        double multiplier = 1.0;
        if (opponent.type.equals("Grass")) {
            multiplier = 1.1;  // Fire is super effective against Grass
            System.out.println("It's super effective!");
        }

        opponent.takeDamage((int)(attackPower * multiplier));
    }

    // ====== Move Management ======

    /**
     * Initialises the FirePokemon's move set
     * Includes Fire Blast, Ember, and Inferno with varying power and accuracy
     */
    @Override
    public void initialiseMoves() {
        moves.add(new Move(
                "Fire Blast",    // High power, medium accuracy
                20,
                Constants.MEDIUM_ACCURACY,
                "A powerful blast of fire"
        ));
        moves.add(new Move(
                "Ember",         // Lower power, high accuracy
                15,
                Constants.HIGH_ACCURACY,
                "A weak but accurate fire attack"
        ));
        moves.add(new Move(
                "Inferno",       // Highest power, low accuracy
                25,
                Constants.LOW_ACCURACY,
                "A devastating but inaccurate attack"
        ));
    }
}
