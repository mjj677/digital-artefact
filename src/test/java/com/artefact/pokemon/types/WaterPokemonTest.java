package com.artefact.pokemon.types;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaterPokemonTest {
    @Test
    void testWaterPokemonCreation() {
        WaterPokemon waterPokemon = WaterPokemon.create("Greninja", 100, 20);
        assertEquals("Greninja", waterPokemon.name);
        assertEquals("Water", waterPokemon.type);
    }
}