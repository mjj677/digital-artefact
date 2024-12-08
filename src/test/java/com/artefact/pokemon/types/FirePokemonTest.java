package com.artefact.pokemon.types;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FirePokemonTest {
    @Test
    void testFirePokemonCreation() {
        FirePokemon firePokemon = FirePokemon.create("Charizard", 100, 20);
        assertEquals("Charizard", firePokemon.name);
        assertEquals("Fire", firePokemon.type);
    }
}