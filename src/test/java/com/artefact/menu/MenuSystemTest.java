package com.artefact.menu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MenuSystemTest {
    @Test
    void testSetUserData() {
        MenuSystem menu = new MenuSystem();
        menu.setUserData("Matt", "Johnston", "Ciptex", 1);
        // Test passes if no exception is thrown
        assertTrue(true);
    }
}