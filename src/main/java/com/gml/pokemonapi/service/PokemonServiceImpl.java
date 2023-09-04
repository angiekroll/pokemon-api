/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.gml.pokemonapi.service;

import com.gml.pokemonapi.clientsfeign.PokemonClient;
import com.gml.pokemonapi.constans.NotificationCode;
import com.gml.pokemonapi.dto.Paging;
import com.gml.pokemonapi.dto.PokemonDto;
import com.gml.pokemonapi.dto.PokemonResponse;
import com.gml.pokemonapi.clientsfeign.PokemonClientResponse;
import com.gml.pokemonapi.exception.PokemonApiException;
import com.gml.pokemonapi.mapper.PokemonMapper;
import com.gml.pokemonapi.model.Pokemon;
import com.gml.pokemonapi.model.repository.PokemonRepository;
import com.gml.pokemonapi.service.impl.PokemonService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  private final PokemonClient pokemonClient;
  private final PokemonRepository pokemonRepository;

  public PokemonServiceImpl(PokemonClient pokemonClient, PokemonRepository pokemonRepository) {
    this.pokemonClient = pokemonClient;
    this.pokemonRepository = pokemonRepository;
  }

  @Override
  public PokemonResponse getPokemons(int page, int pageSize) throws PokemonApiException {

    // TODO: se calcula el offset aquí para que desde el front envien paginas de 1 a 'n' y no desde
    //  0 a 'n', pero se puede dejar también desde 0 a 'n', es relativo.
    int offset = (page - 1) * pageSize;

    ResponseEntity<PokemonClientResponse> response = pokemonClient.getPokemons(offset, pageSize);

    validateResponse(response);

    List<PokemonDto> pokemonDtos = response.getBody().getResults();
    Long totalPokemons = response.getBody().getCount().longValue();
    int totalPages = (int) Math.ceil((double) totalPokemons / pageSize);

    return PokemonResponse.builder()
        .pokemonDtos(pokemonDtos)
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
      log.error("Error getting pokemonDtos from external API");
      throw new PokemonApiException(NotificationCode.ERROR_GETTING_EXTERNAL_SERVICE);
    }
  }

  @Override
  public PokemonResponse getPokemonsDb(int page, int pageSize) {

    Pageable pageable = PageRequest.of(page, pageSize);
    Page<Pokemon> pokemonPage = pokemonRepository.findAll(pageable);

    List<PokemonDto> pokemonDtos = new ArrayList<>();
    pokemonPage.getContent()
        .forEach(pokemon -> pokemonDtos.add(PokemonMapper.INSTANCE.pokemonToPokemonDTO(pokemon)));

    return PokemonResponse.builder()
        .pokemonDtos(pokemonDtos)
        .paging(Paging.builder()
            .page(pokemonPage.getPageable().getPageNumber())
            .pageSize(pokemonPage.getPageable().getPageSize())
            .totalPages(pokemonPage.getTotalPages())
            .total(pokemonPage.getTotalElements())
            .build())
        .build();
  }

}