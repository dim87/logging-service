package com.example.logging.domain.logrecords;


import com.example.logging.domain.loglevels.LogLevel;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class LogRecordData {

  private UUID id;
  private LocalDateTime timestamp;
  private LogLevel level;
  private String applicationCode;
  private String revision;
  private String thread;
  private String file;
  private Integer line;
  private String message;
  private String exception;
}
