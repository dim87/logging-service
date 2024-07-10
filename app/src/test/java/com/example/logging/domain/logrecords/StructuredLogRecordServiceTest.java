package com.example.logging.domain.logrecords;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.logging.domain.loglevels.LogLevel;
import com.example.logging.domain.logrecords.save.SaveStructuredLogRequest;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StructuredLogRecordServiceTest {

  @Autowired
  private StructuredLogRecordService structuredLogRecordService;

  @Test
  void testSaveSavesRecordCorrectly() {
    val request = new SaveStructuredLogRequest(
        1234567890L,
        "ERROR",
        "my-application",
        "abc123",
        "main",
        "LogService.java",
        42,
        "This is an error message",
        "java.lang.RuntimeException: Something went wrong"
    );

    val saved = structuredLogRecordService.save(request);

    assertEquals(structuredLogRecordService.getLocalDateTime(request.timestamp()),
        saved.getTimestamp());
    assertEquals(LogLevel.from(request.level()), saved.getLevel());
    assertEquals(request.applicationCode(), saved.getApplicationCode());
    assertEquals(request.revision(), saved.getRevision());
    assertEquals(request.thread(), saved.getThread());
    assertEquals(request.file(), saved.getFile());
    assertEquals(request.line(), saved.getLine());
    assertEquals(request.message(), saved.getMessage());
    assertEquals(request.exception(), saved.getException());
  }
}
