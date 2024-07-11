package com.example.logging.domain.logrecords;

import com.example.logging.domain.applications.ApplicationService;
import com.example.logging.domain.loglevels.LogLevelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogAnalysisService {

  private static final long RETURNED_LOG_RECORD_LIMIT = 500;

  private final ApplicationService applicationService;
  private final LogLevelService logLevelService;
  private final LogRecordRepository logRecordRepository;

  private final LogRecordMapperService logRecordMapperService;

  @Transactional(readOnly = true)
  public List<LogRecordData> getLogs(String levelCode, long offset) {
    val level = logLevelService.require(levelCode);

    val logRecords = logRecordRepository.findAllByLevelId(level.getId());

    val levels = logLevelService.findAll();
    val applications = applicationService.findAll();

    return logRecordMapperService.map(logRecords, levels, applications);
  }
}
