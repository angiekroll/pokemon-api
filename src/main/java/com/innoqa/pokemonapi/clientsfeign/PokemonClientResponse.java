/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.clientsfeign;

import com.innoqa.pokemonapi.dto.Pokemon;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonClientResponse {

  private Number count;
  private String next;
  private String previous;
  private List<Pokemon> results;

}