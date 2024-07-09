package com.example.logging.domain.logging.save;

import com.example.logging.domain.logging.LogLevel;
import java.util.UUID;

public record SaveStructuredLogRequest(
    UUID id,
    String applicationId,
    long timestamp,
    LogLevel level,
    String thread,
    String file,
    int line,
    String message,
    String exception
) {

}
