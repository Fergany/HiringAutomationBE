package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
