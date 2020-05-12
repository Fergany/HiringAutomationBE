package com.orange.hiring_automation.repository;

import com.orange.hiring_automation.model.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
    Job findById(long id);
}
