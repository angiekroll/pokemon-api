/**
 * Copyright 2023, Neoris. All rights reserved Date: 29/08/23
 */
package com.gml.pokemonapi.constans;

import org.springframework.http.HttpStatus;

/**
 * @author angiekroll@gmail.com - Ángela Carolina Castillo Rodríguez.
 * @version - 1.0.0
 * @since - 1.0.0
 */
public enum NotificationCode {
  ERROR_GETTING_EXTERNAL_SERVICE(
      "An error occurred while establishing a connection to the external api",
      HttpStatus.SERVICE_UNAVAILABLE);

  private final HttpStatus httpStatus;
  private final String message;

  NotificationCode(String message, HttpStatus httpStatus) {
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public String getDescription() {
    return message;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

}