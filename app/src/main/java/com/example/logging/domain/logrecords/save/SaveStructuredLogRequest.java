package com.example.logging.domain.logrecords.save;

import com.example.logging.domain.loglevels.LogLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SaveStructuredLogRequest(

    @NotNull Long timestamp,
    @NotNull @Valid @Pattern(regexp = "^(ERROR|DEBUG|INFO|WARN)$") String level,
    @NotNull String applicationCode,
    @Size(max = 255) String revision,
    @NotBlank @Size(max = 255) String thread,
    @NotBlank @Size(max = 255) String file,
    @NotNull Integer line,
    @Size(max = 1000) String message,
    @Size(max = 5000) String exception
) {

}
