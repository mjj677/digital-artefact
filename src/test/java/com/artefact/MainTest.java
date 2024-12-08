package com.artefact;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testCreateSummary() {
        String summary = Main.createSummary("Matt", "Johnston", "Ciptex", 1);
        assertTrue(summary.contains("Matt Johnston"));
        assertTrue(summary.contains("Ciptex"));
        assertTrue(summary.contains("1 year"));
    }
}