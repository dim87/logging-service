package com.example.logging.controllers;

import com.example.logging.domain.logrecords.LogAnalysisService;
import com.example.logging.domain.logrecords.LogRecordData;
import com.example.logging.domain.logrecords.StructuredLogRecordService;
import com.example.logging.domain.logrecords.requests.SaveStructuredLogRequest;
import com.example.logging.utils.GenericResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LoggingController {

  private final StructuredLogRecordService structuredLogRecordService;
  private final LogAnalysisService logAnalysisService;

  @PostMapping("/structured")
  public GenericResponse<LogRecordData> saveStructuredLogEntry(
      @Validated @RequestBody SaveStructuredLogRequest request) {

    val id = structuredLogRecordService.save(request);
    return GenericResponse.success(id);
  }

  @PostMapping()
  public GenericResponse<Void> saveStructuredLogEntry(@RequestBody String logMessage) {
    log.info(logMessage);
    return GenericResponse.failure("Not yet implemented");
  }

  @GetMapping()
  public GenericResponse<List<LogRecordData>> getLogs(@RequestParam String level,
      @RequestParam long offset) {
    val logs = logAnalysisService.getLogs(level, offset);
    return GenericResponse.success(logs);
  }
}
