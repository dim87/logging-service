package com.example.logging.domain.logrecords;

import com.example.logging.domain.applications.ApplicationService;
import com.example.logging.domain.loglevels.LogLevel;
import com.example.logging.domain.logrecords.save.SaveStructuredLogRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Slf4j
@RequiredArgsConstructor
public class StructuredLogRecordService {

  private final ApplicationService applicationService;
  private final LogRecordRepository logRecordRepository;

  private final ModelMapper modelMapper;

  @Transactional
  public LogRecordData save(@Validated SaveStructuredLogRequest request) {

    val application = applicationService.getOrCreate(request.applicationCode());
    val dateTime = getLocalDateTime(request.timestamp());

    val logRecord = new LogRecordEntity();
    logRecord.setTimestamp(dateTime);
    logRecord.setLevelId(1L);
    logRecord.setApplicationId(application.getId());
    logRecord.setRevision(request.revision());
    logRecord.setThread(request.thread());
    logRecord.setFile(request.file());
    logRecord.setLine(request.line());
    logRecord.setMessage(request.message());
    logRecord.setException(request.exception());

    logRecordRepository.save(logRecord);

    return mapEntity(logRecord, LogLevel.DEBUG, application.getCode());
  }

  private LogRecordData mapEntity(@NonNull LogRecordEntity entity, @NonNull LogLevel level,
      @NonNull String applicationCode) {
    val mapped = modelMapper.map(entity, LogRecordData.class);
    mapped.setApplicationCode(applicationCode);
    mapped.setLevel(level);
    return mapped;
  }

  private LocalDateTime getLocalDateTime(long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
        ZoneId.systemDefault());
  }
}
