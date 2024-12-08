package com.artefact.pokemon;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MoveTest {
    @Test
    void testMoveCreation() {
        Move move = new Move("Fire Blast", 20, 0.85, "A powerful fire attack");
        assertEquals("Fire Blast", move.getName());
        assertEquals(20, move.getBasePower());
    }
}