package com.artefact.pokemon.types;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrassPokemonTest {
    @Test
    void testGrassPokemonCreation() {
        GrassPokemon grassPokemon = GrassPokemon.create("Sceptile", 100, 20);
        assertEquals("Sceptile", grassPokemon.name);
        assertEquals("Grass", grassPokemon.type);
    }
}