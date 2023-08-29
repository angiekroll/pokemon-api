/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.clientsfeign;

import com.innoqa.pokemonapi.model.PokemonClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@FeignClient(name = "pokemon-api", url = "https://pokeapi.co/api/v2")
public interface PokemonClient {

  @GetMapping("/pokemon")
  PokemonClientResponse getPokemons();

}