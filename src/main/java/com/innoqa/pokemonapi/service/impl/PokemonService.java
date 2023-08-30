/**
 * Copyright 2023. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.service.impl;

import com.innoqa.pokemonapi.dto.PokemonResponse;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public interface PokemonService {

  PokemonResponse getPokemons(int page, int pageSize);


}