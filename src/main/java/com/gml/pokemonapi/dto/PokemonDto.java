/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.gml.pokemonapi.dto;

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
public class PokemonDto {

  private String name;
  private String url;

}