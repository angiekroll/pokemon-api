/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.innoqa.pokemonapi.exception;

import com.innoqa.pokemonapi.constans.NotificationCode;
import lombok.Getter;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public class PokemonApiException extends Exception {

  @Getter
  private final NotificationCode errorCode;

  public PokemonApiException(NotificationCode errorCode) {
    super(errorCode.getDescription());
    this.errorCode = errorCode;
  }

  public PokemonApiException(NotificationCode errorCode, Throwable cause) {
    super(errorCode.getDescription(), cause);
    this.errorCode = errorCode;
  }

}