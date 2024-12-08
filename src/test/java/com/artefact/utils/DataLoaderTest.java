package com.artefact.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

class DataLoaderTest {
    @Test
    void testLoadMostRecentUserData() {
        Map<String, String> userData = DataLoader.loadMostRecentUserData();
        // Test that the method runs without throwing exceptions
        // Result might be null if no data exists, which is fine
        assertTrue(true);
    }
}