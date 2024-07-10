package com.example.logging.domain.loglevels;

import lombok.NonNull;

public enum LogLevel {

  ERROR,
  WARN,
  INFO,
  DEBUG;

  public static LogLevel from(@NonNull String level) {
    return LogLevel.valueOf(level.toUpperCase());
  }
}
