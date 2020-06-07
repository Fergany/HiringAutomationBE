package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.Job;
import com.orange.hiring_automation.model.JobSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSubmissionRepository extends JpaRepository<JobSubmission, Long> {
    List<JobSubmission> findByJob(Job job);
}
