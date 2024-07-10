package com.example.logging.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse<T> {

  private Boolean success;
  private String message;
  private T data;

  protected GenericResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  protected GenericResponse(boolean success, String message, T data) {
    this.success = success;
    this.message = message;
    this.data = data;
  }

  public static <T> GenericResponse<T> success(T data) {
    return new GenericResponse<>(true, null, data);
  }

  public static <T> GenericResponse<T> success() {
    return new GenericResponse<>(true, null);
  }

  public static <T> GenericResponse<T> failure(String message) {
    return new GenericResponse<>(false, message);
  }

  public static <T> GenericResponse<T> failure(String message, T data) {
    return new GenericResponse<>(false, message, data);
  }
}
