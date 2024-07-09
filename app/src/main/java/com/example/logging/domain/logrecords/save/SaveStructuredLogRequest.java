package com.example.logging.domain.logrecords.save;

import com.example.logging.domain.loglevels.LogLevel;

public record SaveStructuredLogRequest(
    long timestamp,
    LogLevel level,
    String applicationCode,
    String revision,
    String thread,
    String file,
    int line,
    String message,
    String exception
) {

}
