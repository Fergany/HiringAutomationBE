package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.JobSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSubmissionRepository extends JpaRepository<JobSubmission, Long> {

}
