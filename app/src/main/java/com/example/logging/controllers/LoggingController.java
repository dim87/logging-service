package com.example.logging.controllers;

import com.example.logging.domain.logrecords.LogRecordData;
import com.example.logging.domain.logrecords.StructuredLogRecordService;
import com.example.logging.domain.logrecords.requests.SaveStructuredLogRequest;
import com.example.logging.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LoggingController {

  private final StructuredLogRecordService structuredLogRecordService;

  @PutMapping("/structured")
  public GenericResponse<LogRecordData> saveStructuredLogEntry(
      @Validated @RequestBody SaveStructuredLogRequest request) {

    val id = structuredLogRecordService.save(request);
    return GenericResponse.success(id);
  }

  @PutMapping()
  public GenericResponse<Void> saveStructuredLogEntry(@RequestBody String logMessage) {
    log.info(logMessage);
    return GenericResponse.failure("Not yet implemented");
  }
}
