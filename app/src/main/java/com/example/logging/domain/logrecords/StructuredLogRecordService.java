package com.example.logging.domain.logrecords;

import com.example.logging.domain.applications.ApplicationService;
import com.example.logging.domain.loglevels.LogLevel;
import com.example.logging.domain.loglevels.LogLevelService;
import com.example.logging.domain.logrecords.requests.SaveStructuredLogRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@RequiredArgsConstructor
public class StructuredLogRecordService {

  private final ApplicationService applicationService;
  private final LogLevelService logLevelService;
  private final LogRecordRepository logRecordRepository;
  private final LogRecordMapperService logRecordMapperService;


  @Transactional
  public LogRecordData save(@Validated SaveStructuredLogRequest request) {

    val application = applicationService.getOrCreate(request.applicationCode());
    val dateTime = getLocalDateTime(request.timestamp());
    val level = logLevelService.require(request.level());

    val logRecord = new LogRecordEntity();
    logRecord.setTimestamp(dateTime);
    logRecord.setLevelId(level.getId());
    logRecord.setApplicationId(application.getId());
    logRecord.setRevision(request.revision());
    logRecord.setThread(request.thread());
    logRecord.setFile(request.file());
    logRecord.setLine(request.line());
    logRecord.setMessage(request.message());
    logRecord.setException(request.exception());

    logRecordRepository.save(logRecord);

    return logRecordMapperService.map(logRecord, LogLevel.from(level.getCode()),
        application.getCode());
  }


  LocalDateTime getLocalDateTime(long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
        ZoneId.systemDefault());
  }
}
