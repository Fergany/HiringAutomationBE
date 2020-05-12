package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.JobNotFoundException;
import com.orange.hiring_automation.model.Job;
import com.orange.hiring_automation.repository.JobRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {
    private final JobRepository repository;

    JobController(JobRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/jobs")
    List<Job> getAll() {
        return repository.findAll();
    }

    @PostMapping("/jobs")
    Job add(@RequestBody Job newJob) {
        return repository.save(newJob);
    }

    @GetMapping("/jobs/{id}")
    Job getById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    @PutMapping("/jobs/{id}")
    Job edit(@RequestBody Job newJob, @PathVariable Long id) {
        return repository.findById(id)
                .map(job -> {
                    job.setTitle(newJob.getTitle());
                    job.setDescription(newJob.getDescription());
                    job.setRequiredSkills(newJob.getRequiredSkills());
                    return repository.save(job);
                })
                .orElseGet(() -> {
                    newJob.setId(id);
                    return repository.save(newJob);
                });
    }

    @DeleteMapping("/jobs/{id}")
    void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
