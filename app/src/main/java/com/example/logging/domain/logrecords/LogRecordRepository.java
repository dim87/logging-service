package com.example.logging.domain.logrecords;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LogRecordRepository extends JpaRepository<LogRecordEntity, Long> {

}
