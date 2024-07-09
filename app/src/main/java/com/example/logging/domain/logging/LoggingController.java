package com.example.logging.domain.logging;

import com.example.logging.domain.logging.save.SaveStructuredLogRequest;
import com.example.logging.domain.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LoggingController {

  @PutMapping("/structured")
  public GenericResponse<Void> saveStructuredLogEntry(@RequestBody SaveStructuredLogRequest request) {
    log.info(request.toString());
    return GenericResponse.success();
  }

  @PutMapping()
  public GenericResponse<Void> saveStructuredLogEntry(@RequestBody String logMessage) {
    log.info(logMessage);
    return GenericResponse.failure("Not yet implemented");
  }
}
