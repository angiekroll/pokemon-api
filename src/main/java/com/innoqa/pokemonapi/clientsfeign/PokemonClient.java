/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.clientsfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@FeignClient(name = "pokemon-api", url = "${external-apis.pokemon-api.url}")
public interface PokemonClient {

  @GetMapping("/pokemon")
  ResponseEntity<PokemonClientResponse> getPokemons(
      @RequestParam("offset") int offset,
      @RequestParam("limit") int limit);

}