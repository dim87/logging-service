package com.example.logging.domain.loglevels;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogLevelService {

  private final LogLevelRepository logLevelRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  public LogLevelData require(@NonNull String code) {
    return logLevelRepository.getByCode(code).map(this::mapEntity).orElseThrow(
        () -> new DataRetrievalFailureException(
            String.format("Log level not found for code: %s", code)));
  }

  private LogLevelData mapEntity(@NonNull LogLevelEntity entity) {
    return modelMapper.map(entity, LogLevelData.class);
  }

  @Transactional(readOnly = true)
  public List<LogLevelData> findAll() {
    return logLevelRepository.findAll().stream().map(this::mapEntity).toList();
  }
}
