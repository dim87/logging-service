package com.example.logging.domain.logrecords.save;

import com.example.logging.domain.loglevels.LogLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SaveStructuredLogRequest(
    @NotNull Long timestamp,
    @NotNull LogLevel level,
    @NotNull String applicationCode,
    String revision,
    @NotBlank String thread,
    @NotBlank String file,
    @NotNull Integer line,
    @Size(max = 1000) String message,
    @Size(max = 5000) String exception
) {

}
