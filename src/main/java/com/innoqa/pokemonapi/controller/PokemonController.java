/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.controller;

import com.innoqa.pokemonapi.constans.EndpointsResources;
import com.innoqa.pokemonapi.dto.PokemonResponse;
import com.innoqa.pokemonapi.exception.PokemonApiException;
import com.innoqa.pokemonapi.service.impl.PokemonService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@RestController
@RequestMapping(EndpointsResources.POKEMON)
@Validated
public class PokemonController {

  private PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }


  @GetMapping
  public ResponseEntity<PokemonResponse> getPokemon(
      @RequestParam(name = "page", defaultValue = "1") @Positive int page,
      @RequestParam(name = "pageSize", defaultValue = "20") @Positive int pageSize)
      throws PokemonApiException {
    return ResponseEntity.ok(pokemonService.getPokemons(page, pageSize));
  }

}