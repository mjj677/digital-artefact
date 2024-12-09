package com.artefact.utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * DataLoader class loads most recent user data, if available.
 */
public class DataLoader {
    
    /**
     * Attempts to load the most recent user data file from the user_data directory
     * @return Map containing user data, or null if no file found/error occurs
     */

    public static Map<String, String> loadMostRecentUserData() {
        File directory = new File(Constants.OUTPUT_DIR);
        File mostRecent = null;
        long lastModified = Long.MIN_VALUE;

        // Loop through the files in user_data and find the most recent one
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(Constants.FILE_EXTENSION));
            // If it finds a file, it continues.
            if (files != null) {
                // Shorthand loop syntax.
                for (File file : files) {
                    if (file.lastModified() > lastModified && !file.getName().contains("battle_history")) {
                        mostRecent = file;
                        lastModified = file.lastModified();
                    }
                }
            }
        }
        if (mostRecent == null) {
            return null;
        }

        /*  I didn't use a HashMap here as doesn't guarantee.
        *   order maintenance. LinkedHashMap does, however there is
        *   slightly more memory overhead, however the data set is never
        *   going to be huge in this context, so i'm not worried.
        */
        Map<String, String> userData = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(mostRecent))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    userData.put(key, value);
                }
            }
            return userData;
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
            return null;
        }
    }
}
