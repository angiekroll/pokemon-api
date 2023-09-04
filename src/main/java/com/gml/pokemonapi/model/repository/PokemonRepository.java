/**
 * Copyright 2023, Company. All rights reserved Date: 1/09/23
 */
package com.gml.pokemonapi.model.repository;

import com.gml.pokemonapi.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}