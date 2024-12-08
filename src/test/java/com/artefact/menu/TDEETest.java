package com.artefact.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TDEETest {
    @Test
    void testCalculateExpenditure() {
        TDEE tdee = new TDEE();
        // Set test values
        tdee.age = 25;
        tdee.weight = 70;
        tdee.height = 175;
        tdee.activityFactor = 1.2;

        double result = tdee.calculateExpenditure();
        assertTrue(result > 0);
    }
}