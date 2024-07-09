package com.example.logging.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse<T> {

  private Boolean success;
  private String message;
  private T data;

  protected GenericResponse(final boolean success, final String message) {
    this.success = success;
    this.message = message;
  }

  public static <T> GenericResponse<T> success(final T data) {
    return new GenericResponse<>(true, null, data);
  }

  public static <T> GenericResponse<T> success() {
    return new GenericResponse<>(true, null);
  }

  public static <T> GenericResponse<T> failure(final String message) {
    return new GenericResponse<>(false, message);
  }
}
