/**
 * Copyright 2023, Neoris. All rights reserved Date: 30/08/23
 */
package com.gml.pokemonapi.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gml.pokemonapi.clientsfeign.PokemonClient;
import com.gml.pokemonapi.clientsfeign.PokemonClientResponse;
import com.gml.pokemonapi.dto.Pokemon;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@SpringBootTest
@AutoConfigureMockMvc
class PokemonControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PokemonClient pokemonClient;

  @Test
  void givenARequestWhitPage1AndPageSize3_WhenGetPokemon_ThenReturnResponsePagingOK()
      throws Exception {

    Mockito.when(pokemonClient.getPokemons(anyInt(), anyInt())).thenReturn(buildPokemonResponse());

    mockMvc.perform(get("/pokemon")
            .param("page", "1")
            .param("pageSize", "3"))

        .andExpect(status().isOk())

        .andExpect(MockMvcResultMatchers.jsonPath("$.pokemons[0].name").value("bulbasaur"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.pokemons[0].url")
            .value("https://pokeapi.co/api/v2/pokemon/1/"))

        .andExpect(MockMvcResultMatchers.jsonPath("$.pokemons[1].name").value("ivysaur"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.pokemons[1].url")
            .value("https://pokeapi.co/api/v2/pokemon/2/"))

        .andExpect(MockMvcResultMatchers.jsonPath("$.pokemons[2].name").value("venusaur"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.pokemons[2].url")
            .value("https://pokeapi.co/api/v2/pokemon/3/"))

        .andExpect(MockMvcResultMatchers.jsonPath("$.paging.page").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.paging.pageSize").value(3))
        .andExpect(MockMvcResultMatchers.jsonPath("$.paging.total").value(1281))
        .andExpect(MockMvcResultMatchers.jsonPath("$.paging.totalPages").value(427));
  }

  ResponseEntity<PokemonClientResponse> buildPokemonResponse() {

    List<Pokemon> pokemons = new ArrayList<>();
    pokemons.add(Pokemon.builder()
        .name("bulbasaur")
        .url("https://pokeapi.co/api/v2/pokemon/1/")
        .build());

    pokemons.add(Pokemon.builder()
        .name("ivysaur")
        .url("https://pokeapi.co/api/v2/pokemon/2/")
        .build());

    pokemons.add(Pokemon.builder()
        .name("venusaur")
        .url("https://pokeapi.co/api/v2/pokemon/3/")
        .build());

    return ResponseEntity.ok(PokemonClientResponse.builder().count(1281)
        .next("https://pokeapi.co/api/v2/pokemon?offset=0&limit=3")
        .previous(null)
        .results(pokemons)
        .build());
  }

}