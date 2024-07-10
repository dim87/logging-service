package com.example.logging.domain.logrecords;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Entity(name = "log_records")
@Data
class LogRecordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  private LocalDateTime timestamp;
  private long levelId;
  private Long applicationId;
  private String revision;
  private String thread;
  private String file;
  private Integer line;
  private String message;
  private String exception;
}
