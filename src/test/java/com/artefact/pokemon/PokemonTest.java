package com.artefact.pokemon;

import org.junit.jupiter.api.Test;
import com.artefact.pokemon.types.FirePokemon;
import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    @Test
    void testTakeDamage() {
        Pokemon pokemon = FirePokemon.create("Charizard", 100, 20);
        pokemon.takeDamage(30);
        assertEquals(70, pokemon.getHealth());
    }
}