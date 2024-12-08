package com.artefact.utils;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
            if (files != null) {
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

        Map<String, String> userData = new HashMap<>();
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
