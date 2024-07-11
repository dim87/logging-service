package com.example.logging.domain.logrecords;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LogRecordRepository extends JpaRepository<LogRecordEntity, Long> {

  List<LogRecordEntity> findAllByLevelId(long levelId);
}
