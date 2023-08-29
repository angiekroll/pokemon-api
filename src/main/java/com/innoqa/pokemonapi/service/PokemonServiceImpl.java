/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.service;

import com.innoqa.pokemonapi.clientsfeign.PokemonClient;
import com.innoqa.pokemonapi.dto.PokemonDto;
import com.innoqa.pokemonapi.model.PokemonClientResponse;
import com.innoqa.pokemonapi.service.impl.PokemonService;
import org.springframework.stereotype.Service;

/**
 *
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Service
public class PokemonServiceImpl implements PokemonService {
  private PokemonClient pokemonClient;

  public PokemonServiceImpl(PokemonClient pokemonClient) {
    this.pokemonClient = pokemonClient;
  }

  @Override
  public PokemonDto getPokemons(int page, int pageSize) {
    PokemonClientResponse pokemonClientResponse =  pokemonClient.getPokemons();
    return PokemonDto.builder()
        .name(pokemonClientResponse.getResults()[0].getName())
        .url(pokemonClientResponse.getResults()[0].getUrl())
        .build();
  }
}