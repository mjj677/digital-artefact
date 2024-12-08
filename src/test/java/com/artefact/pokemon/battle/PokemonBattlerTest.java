package com.artefact.pokemon.battle;

import org.junit.jupiter.api.Test;
import com.artefact.pokemon.types.FirePokemon;
import com.artefact.pokemon.types.WaterPokemon;
import static org.junit.jupiter.api.Assertions.*;

class PokemonBattlerTest {
    @Test
    void testGenerateOpponent() {
        PokemonBattler battler = new PokemonBattler();
        FirePokemon playerPokemon = FirePokemon.create("Charizard", 100, 20);
        assertNotNull(battler.generateOpponent(playerPokemon));
    }
}