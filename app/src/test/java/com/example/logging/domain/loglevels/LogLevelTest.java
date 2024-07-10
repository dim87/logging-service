package com.example.logging.domain.loglevels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LogLevelTest {

  @ParameterizedTest
  @ValueSource(strings = {"ERROR", "WARN", "INFO", "DEBUG"})
  void testFromValidLevels(String level) {
    LogLevel expectedLevel = LogLevel.valueOf(level.toUpperCase());
    assertEquals(expectedLevel, LogLevel.from(level));
  }

  @Test
  void testFromInvalidLevel() {
    String invalidLevel = "INVALID";
    assertThrows(IllegalArgumentException.class, () -> LogLevel.from(invalidLevel));
  }
}
