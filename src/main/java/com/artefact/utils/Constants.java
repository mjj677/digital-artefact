package com.artefact.utils;

/**
 * Constants class stores application-wide constant values.
 * This ensures consistency across different parts of the application.
 * Follows single source of truth.
 */
public final class Constants {
    // Prevent instantiation
    private Constants() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    // File System Constants
    public static final String OUTPUT_DIR = "user_data";
    public static final String FILE_EXTENSION = ".txt";

    // File Content Constants
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final String FILE_DATE_FORMAT = "yyyy-MM-dd";

    // Game Constants
    public static final int MAX_HEALTH = 100;

    // Battle Constants
    public static final double TYPE_ADVANTAGE_MULTIPLIER = 1.1;
    public static final int BASE_HEAL_AMOUNT = 30;
    public static final int MAX_BATTLE_HISTORY = 5;

    // Move Accuracy Constants
    public static final double HIGH_ACCURACY = 0.95;
    public static final double MEDIUM_ACCURACY = 0.85;
    public static final double LOW_ACCURACY = 0.70;
}