package com.example.logging.domain.logrecords;

import com.example.logging.domain.applications.ApplicationData;
import com.example.logging.domain.loglevels.LogLevel;
import com.example.logging.domain.loglevels.LogLevelData;
import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
class LogRecordMapperService {

  private final ModelMapper modelMapper;

  LogRecordData map(@NonNull LogRecordEntity entity, @NonNull LogLevel level,
      @NonNull String applicationCode) {
    val mapped = modelMapper.map(entity, LogRecordData.class);
    mapped.setApplicationCode(applicationCode);
    mapped.setLevel(level);
    return mapped;
  }

  List<LogRecordData> map(@NonNull List<LogRecordEntity> entities,
      @NonNull List<LogLevelData> logLevels,
      @NonNull List<ApplicationData> applications) {

    val dataObjects = new ArrayList<LogRecordData>(entities.size());
    for (val entity : entities) {
      val mapped = modelMapper.map(entity, LogRecordData.class);
      mapped.setApplicationCode(getApplicationCode(entity.getApplicationId(), applications));
      mapped.setLevel(getLogLevel(entity.getLevelId(), logLevels));
      dataObjects.add(mapped);
    }

    return dataObjects;
  }

  private LogLevel getLogLevel(long levelId, List<LogLevelData> logLevels) {
    return logLevels.stream().filter(item -> item.getId() == levelId)
        .map(LogLevelData::getCode)
        .map(LogLevel::from)
        .findFirst()
        .orElseThrow(() -> new IllegalStateException(
            String.format("Log level with id %s not found", levelId)));
  }

  private String getApplicationCode(long applicationId, List<ApplicationData> applications) {
    return applications.stream().filter(item -> item.getId() == applicationId)
        .map(ApplicationData::getCode).findFirst()
        .orElseThrow(() -> new IllegalStateException(
            String.format("Application with id %s not found", applicationId)));
  }

}
