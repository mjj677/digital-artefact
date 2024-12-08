package com.artefact.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WriteFileTest {
    @Test
    void testTimeFormatter() {
        WriteFile writeFile = new WriteFile();
        String timeStamp = writeFile.timeFormatter();
        assertNotNull(timeStamp);
        assertTrue(timeStamp.matches("\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2}"));
    }
}