package com.example.logging.domain.loglevels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataRetrievalFailureException;

@SpringBootTest
class LogLevelServiceTest {

  @Autowired
  private LogLevelService logLevelService;

  @ParameterizedTest
  @ValueSource(strings = {"ERROR", "WARN", "INFO", "DEBUG"})
  void testRequireReturnsCorrectLevel(String level) {
    val code = logLevelService.require(level);
    assertEquals(code.getCode(), level);
  }

  @Test
  void testRequireThrowsDataRetrievalFailureExceptionWhenEntityIsNotFound() {
    val exception = assertThrows(DataRetrievalFailureException.class, () -> {
      logLevelService.require("INVALID_CODE");
    });

    assertEquals("Log level not found for code: INVALID_CODE", exception.getMessage());
  }
}
