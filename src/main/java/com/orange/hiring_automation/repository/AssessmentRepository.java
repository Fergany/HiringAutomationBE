package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
