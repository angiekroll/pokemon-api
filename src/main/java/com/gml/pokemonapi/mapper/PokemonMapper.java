/**
 * Copyright 2023. All rights reserved Date: 4/09/23
 */
package com.gml.pokemonapi.mapper;

import com.gml.pokemonapi.dto.PokemonDto;
import com.gml.pokemonapi.model.Pokemon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Mapper
public interface PokemonMapper {

  PokemonMapper INSTANCE = Mappers.getMapper(PokemonMapper.class);

//  @Mapping(source = "product.id", target = "productId")
//  @Mapping(source = "brand.id", target = "brandId")
//  @Mapping(source = "id", target = "priceId")
  PokemonDto pokemonToPokemonDTO(Pokemon pokemon);

}