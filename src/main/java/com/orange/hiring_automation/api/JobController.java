package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.JobNotFoundException;
import com.orange.hiring_automation.model.Job;
import com.orange.hiring_automation.repository.JobRepository;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = {"Jobs API"}, description = "Operations pertaining to jobs in Hiring Automation System", produces = "application/json")
public class JobController {
    private final JobRepository repository;

    JobController(JobRepository repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "View a list of available jobs", response = List.class)
    @GetMapping("/jobs")
    List<Job> getAll() {
        return repository.findAll();
    }

    @ApiOperation(value = "Add a job")
    @PostMapping("/jobs")
    Job add(@ApiParam(value = "Job object store in database table") @Valid @RequestBody Job newJob) {
        return repository.save(newJob);
    }

    @ApiOperation(value = "Get a job by Id")
    @GetMapping("/jobs/{id}")
    Job getById(@ApiParam(value = "Job id from which job object will retrieve", required = true) @PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    @ApiOperation(value = "Update job")
    @PutMapping("/jobs/{id}")
    Job edit(
            @ApiParam(value = "Update job object", required = true) @Valid @RequestBody Job newJob,
            @ApiParam(value = "Job Id to update job object", required = true) @PathVariable Long id) {
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

    @ApiOperation(value = "Delete a job")
    @DeleteMapping("/jobs/{id}")
    void delete(
            @ApiParam(value = "Employee Id from which employee object will delete from database table", required = true) @PathVariable Long id) {
        repository.deleteById(id);
    }
}
