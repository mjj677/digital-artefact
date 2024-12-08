package com.artefact.pokemon;

import com.artefact.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing a Pokemon.
 * Defines core Pokemon attributes and behaviours including health management,
 * move sets, healing capabilities and battle mechanics.
 */
public abstract class Pokemon {

    // ====== Class Fields ======

    public String name;          // Pokemon's name
    public String type;          // Pokemon's elemental type
    public int health;           // Current health points
    public int attackPower;      // Base attack damage
    public int potions = 1;      // Available healing potions
    public int healCount = 2;    // Available healing moves
    public List<Move> moves;     // List of battle moves
    public String description;   // Pokemon's description text

    // ====== Constructor ======

    /**
     * Creates a new Pokemon with specified base attributes
     * @param name Pokemon's name
     * @param type Pokemon's elemental type
     * @param health Starting health points
     * @param attackPower Base attack damage
     * @param description Pokemon's descriptive text
     */
    protected Pokemon(String name, String type, int health, int attackPower, String description) {
        this.name = name;
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
        this.description = description;
        this.moves = new ArrayList<>();
    }

    // ====== Factory Methods ======

    /**
     * Factory method to create Pokemon instances
     * @param name Pokemon's name
     * @param type Pokemon's elemental type
     * @param health Starting health points
     * @param attackPower Base attack damage
     * @param description Pokemon's descriptive text
     * @param pokemonClass Specific Pokemon type class
     * @return New Pokemon instance
     */
    @SuppressWarnings("UseSpecificCatch")
    public static Pokemon createPokemon(String name, String type, int health, int attackPower,
                                        String description, Class<? extends Pokemon> pokemonClass) {
        try {
            Pokemon pokemon = pokemonClass.getDeclaredConstructor(String.class, int.class, int.class)
                    .newInstance(name, health, attackPower);
            pokemon.initialiseMoves();
            return pokemon;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Pokemon: " + e.getMessage());
        }
    }

    /**
     * Initialises the Pokemon's move set
     * To be implemented by specific Pokemon types
     */
    protected void initialiseMoves() {
        moves = new ArrayList<>();
    }

    // ====== Battle Methods ======

    /**
     * Creates a visual representation of the Pokemon's health as a progress bar
     * @return String containing health bar and current/max health values
     */
    public String getHealthBar() {
        int barLength = 20;
        int filledBars = (int)((double)health / Constants.MAX_HEALTH * barLength);
        StringBuilder healthBar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            healthBar.append(i < filledBars ? "█" : "░");
        }
        healthBar.append("] ").append(health).append("/").append(Constants.MAX_HEALTH);
        return healthBar.toString();
    }

    /**
     * Abstract attack method to be implemented by specific Pokemon types
     * @param opponent Target Pokemon to attack
     */
    public abstract void attack(Pokemon opponent);

    /**
     * Applies damage to the Pokemon's health
     * @param damage Amount of damage to apply
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) this.health = 0;
    }

    // ====== Healing Methods ======

    /**
     * Attempts to use a healing potion
     * Heals up to 30 HP without exceeding max health
     */
    public void heal() {
        if (potions > 0) {
            int healAmount = Constants.BASE_HEAL_AMOUNT;
            if (health + healAmount > Constants.MAX_HEALTH) {
                healAmount = Constants.MAX_HEALTH - health;
            }
            health += healAmount;
            potions--;
            System.out.println(name + " used a potion and recovered " + healAmount + " HP!");
        } else {
            System.out.println("No potions remaining!");
        }
    }

    /**
     * Computer-controlled healing action
     * Heals random amount between 10-30 HP without exceeding max health
     */
    public void computerHeal() {
        if (healCount > 0) {
            int healAmount = (int)(Math.random() * 21) + 10;
            if (health + healAmount > Constants.MAX_HEALTH) {
                healAmount = Constants.MAX_HEALTH - health;
            }
            health += healAmount;
            healCount--;
            System.out.println(name + " used a heal and recovered " + healAmount + " HP!");
        }
    }

    // ====== Utility Methods ======

    /**
     * @return Current health points
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Checks if Pokemon still has health remaining
     * @return true if health > 0, false otherwise
     */
    public boolean isAlive() {
        return this.health > 0;
    }

    /**
     * Checks if Pokemon has healing moves available
     * @return true if healCount > 0, false otherwise
     */
    public boolean hasHeals() {
        return healCount > 0;
    }

    /**
     * Creates string representation of Pokemon
     * @return Formatted string with name, type and health
     */
    @Override
    public String toString() {
        return name + " (" + type + " Type) - Health: " + health;
    }
}

