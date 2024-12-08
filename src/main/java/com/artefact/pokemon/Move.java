package com.artefact.pokemon;

/**
 * Move class represents a Pokemon's battle move.
 * It encapsulates the move's characteristics including name, power,
 * accuracy and description, and handles hit probability calculations.
 */
public class Move {

    // ====== Class Fields ======

    private final String name;          // Name of the move
    private final int basePower;        // Base damage of the move
    private final double accuracy;      // Hit probability (0.0 to 1.0)
    private final String description;   // Move description text

    // ====== Constructor ======

    /**
     * Creates a new Move with specified characteristics
     * @param name Name of the move
     * @param basePower Base damage power of the move
     * @param accuracy Hit probability (between 0.0 and 1.0)
     * @param description Text description of the move
     */
    public Move(String name, int basePower, double accuracy, String description) {
        this.name = name;
        this.basePower = basePower;
        this.accuracy = accuracy;
        this.description = description;
    }

    // ====== Battle Methods ======

    /**
     * Determines if the move successfully hits based on accuracy
     * @return true if the move hits, false if it misses
     */
    public boolean attemptHit() {
        return Math.random() <= accuracy;
    }

    // ====== Getter Methods ======

    /**
     * @return Name of the move
     */
    public String getName() {
        return name;
    }

    /**
     * @return Base power of the move
     */
    public int getBasePower() {
        return basePower;
    }

    /**
     * @return Description of the move
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Accuracy of the move (0.0 to 1.0)
     */
    public double getAccuracy() {
        return accuracy;
    }
}
