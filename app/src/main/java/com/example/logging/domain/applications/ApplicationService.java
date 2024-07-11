package com.example.logging.domain.applications;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationService {

  private final ApplicationRepository applicationRepository;
  private final ModelMapper modelMapper;

  @Transactional(readOnly = true)
  public ApplicationData getOrCreate(@NonNull String code) {
    val entity = applicationRepository.getByCode(code);

    return entity.map(this::mapEntity)
        .orElseGet(() -> this.mapEntity(create(code)));
  }

  private ApplicationEntity create(String code) {
    val entity = new ApplicationEntity();
    entity.setCode(code);
    return applicationRepository.save(entity);
  }

  private ApplicationData mapEntity(@NonNull ApplicationEntity entity) {
    return modelMapper.map(entity, ApplicationData.class);
  }

  @Transactional(readOnly = true)
  public List<ApplicationData> findAll() {
    return applicationRepository.findAll().stream().map(this::mapEntity).toList();
  }
}
