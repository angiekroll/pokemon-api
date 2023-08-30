/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.service;

import com.innoqa.pokemonapi.clientsfeign.PokemonClient;
import com.innoqa.pokemonapi.dto.Paging;
import com.innoqa.pokemonapi.dto.Pokemon;
import com.innoqa.pokemonapi.dto.PokemonResponse;
import com.innoqa.pokemonapi.clientsfeign.PokemonClientResponse;
import com.innoqa.pokemonapi.service.impl.PokemonService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Service
@Slf4j
public class PokemonServiceImpl implements PokemonService {

  private PokemonClient pokemonClient;

  public PokemonServiceImpl(PokemonClient pokemonClient) {
    this.pokemonClient = pokemonClient;
  }

  @Override
  public PokemonResponse getPokemons(int page, int pageSize) {

    int offset = (page - 1) * pageSize;

    PokemonClientResponse pokemonClientResponse = pokemonClient.getPokemons(offset, pageSize);

    List<Pokemon> pokemons = pokemonClientResponse.getResults();
    int totalPokemons = (int) pokemonClientResponse.getCount();
    int totalPages = (int) Math.ceil((double) totalPokemons / pageSize);

    return PokemonResponse.builder()
        .pokemons(pokemons)
        .paging(Paging.builder()
            .page(page)
            .pageSize(pageSize)
            .totalPages(totalPages)
            .total(totalPokemons)
            .build())
        .build();
  }

}