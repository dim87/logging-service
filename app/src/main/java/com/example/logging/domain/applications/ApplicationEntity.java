package com.example.logging.domain.applications;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "applications")
@Data
class ApplicationEntity {

  @Id
  @Column(name = "id", columnDefinition = "bigserial")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;
}
