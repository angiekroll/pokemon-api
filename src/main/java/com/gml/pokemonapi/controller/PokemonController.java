/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.gml.pokemonapi.controller;

import com.gml.pokemonapi.constans.EndpointsResources;
import com.gml.pokemonapi.exception.PokemonApiException;
import com.gml.pokemonapi.dto.PokemonResponse;
import com.gml.pokemonapi.service.impl.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

  private final PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @Operation(summary = "Get a list of paginated pokemon from an external service get")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation", content = {
          @Content(mediaType = "application/json", schema = @Schema(implementation = PokemonResponse.class))
      }),
      @ApiResponse(responseCode = "503", description = "Internal server error", content = @Content)
  })

  @GetMapping
  public ResponseEntity<PokemonResponse> getPokemon(
      @RequestParam(name = "page", defaultValue = "1") @Positive int page,
      @RequestParam(name = "pageSize", defaultValue = "20") @Positive int pageSize)
      throws PokemonApiException {
    return ResponseEntity.ok(pokemonService.getPokemons(page, pageSize));
  }

  @GetMapping("/pokemons-db")
  public ResponseEntity<PokemonResponse> getPokemonFromDataBase(
      @RequestParam(name = "page", defaultValue = "1") @Positive int page,
      @RequestParam(name = "pageSize", defaultValue = "20") @Positive int pageSize)
      throws PokemonApiException {
    return ResponseEntity.ok(pokemonService.getPokemonsDb(page, pageSize));
  }

}