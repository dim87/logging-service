package com.example.logging.domain.loglevels;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LogLevelRepository extends JpaRepository<LogLevelEntity, Long> {

  Optional<LogLevelEntity> getByCode(String code);
}
