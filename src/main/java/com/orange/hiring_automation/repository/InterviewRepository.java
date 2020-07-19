package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
}
