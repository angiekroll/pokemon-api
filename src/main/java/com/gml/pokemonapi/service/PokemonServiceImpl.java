/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.gml.pokemonapi.service;

import com.gml.pokemonapi.clientsfeign.PokemonClient;
import com.gml.pokemonapi.constans.NotificationCode;
import com.gml.pokemonapi.dto.Paging;
import com.gml.pokemonapi.dto.Pokemon;
import com.gml.pokemonapi.dto.PokemonResponse;
import com.gml.pokemonapi.clientsfeign.PokemonClientResponse;
import com.gml.pokemonapi.exception.PokemonApiException;
import com.gml.pokemonapi.service.impl.PokemonService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public PokemonResponse getPokemons(int page, int pageSize) throws PokemonApiException {

    int offset = (page - 1) * pageSize;

    ResponseEntity<PokemonClientResponse> response = pokemonClient.getPokemons(offset, pageSize);

    validateResponse(response);

    List<Pokemon> pokemons = response.getBody().getResults();
    int totalPokemons = (int) response.getBody().getCount();
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

  private void validateResponse(ResponseEntity<?> response) throws PokemonApiException {
    if (response.getStatusCode() != HttpStatus.OK) {
      log.error("Error getting pokemons from external API");
      throw new PokemonApiException(NotificationCode.ERROR_GETTING_EXTERNAL_SERVICE);
    }
  }

}