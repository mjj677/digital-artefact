package com.artefact.pokemon.types;

import com.artefact.pokemon.Pokemon;
import com.artefact.pokemon.Move;

/**
 * GrassPokemon class represents grass-type Pokemon.
 * Implements specific grass-type moves and type effectiveness calculations.
 * Inherits base Pokemon attributes and behaviours.
 */
public class GrassPokemon extends Pokemon {

    // ====== Constructor ======

    /**
     * Creates a new Grass-type Pokemon
     * @param name Pokemon's name
     * @param health Starting health points
     * @param attackPower Base attack damage
     */
    public GrassPokemon(String name, int health, int attackPower) {
        super(name, "Grass", health, attackPower,
                "It fills its body with power. While it basks in the sun, it can convert " +
                        "the light into energy. As a result, it is more powerful in the summertime.");
    }

    // ====== Factory Methods ======

    /**
     * Factory method to create new GrassPokemon instances
     * @param name Pokemon's name
     * @param health Starting health points
     * @param attackPower Base attack damage
     * @return New GrassPokemon instance
     */
    public static GrassPokemon create(String name, int health, int attackPower) {
        return (GrassPokemon) Pokemon.createPokemon(
                name,
                "Grass",
                health,
                attackPower,
                "It fills its body with power. While it basks in the sun, it can convert " +
                        "the light into energy. As a result, it is more powerful in the summertime.",
                GrassPokemon.class
        );
    }

    // ====== Battle Methods ======

    /**
     * Executes attack against opponent Pokemon
     * Applies type effectiveness multiplier against Water-type Pokemon
     * @param opponent Target Pokemon to attack
     */
    @Override
    public void attack(Pokemon opponent) {
        System.out.println(this.name + " uses Vine Whip!");

        // Calculate damage with type multiplier
        double multiplier = 1.0;
        if (opponent.type.equals("Water")) {
            multiplier = 1.1;  // Grass is super effective against Water
            System.out.println("It's super effective!");
        }

        opponent.takeDamage((int)(attackPower * multiplier));
    }

    // ====== Move Management ======

    /**
     * Initialises the GrassPokemon's move set
     * Includes Leaf Storm, Leaf Blade, and Solar Beam with varying power and accuracy
     */
    @Override
    public void initialiseMoves() {
        moves.add(new Move(
                "Leaf Storm",    // High power, medium accuracy
                20,
                0.7,
                "A powerful hurricane of leaves!"
        ));
        moves.add(new Move(
                "Leaf Blade",    // Lower power, high accuracy
                15,
                0.95,
                "A weak but accurate leaf attack"
        ));
        moves.add(new Move(
                "Solar Beam",    // Highest power, lowest accuracy
                25,
                0.6,
                "A devastating but inaccurate attack."
        ));
    }
}