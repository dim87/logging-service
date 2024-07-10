package com.example.logging.domain.applications;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

  Optional<ApplicationEntity> getByCode(String code);
}
