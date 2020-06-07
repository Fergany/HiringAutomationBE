package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.ObjectNotFoundException;
import com.orange.hiring_automation.model.Candidate;
import com.orange.hiring_automation.model.Job;
import com.orange.hiring_automation.model.JobSubmission;
import com.orange.hiring_automation.model.FileUploaded;
import com.orange.hiring_automation.repository.CandidateRepository;
import com.orange.hiring_automation.repository.FileUploadedRepository;
import com.orange.hiring_automation.repository.JobRepository;
import com.orange.hiring_automation.repository.JobSubmissionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@Api(tags = {"Jobs API"}, description = "Operations pertaining to jobs in Hiring Automation System", produces = "application/json")
public class JobController {
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final JobSubmissionRepository jobSubmissionRepository;
    private final FileUploadedRepository fileUploadedRepository;

    JobController(JobRepository jobRepository, CandidateRepository candidateRepository, JobSubmissionRepository jobSubmissionRepository, FileUploadedRepository fileUploadedRepository) {
        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
        this.jobSubmissionRepository = jobSubmissionRepository;
        this.fileUploadedRepository = fileUploadedRepository;
    }

    @ApiOperation(value = "View a list of available jobs", response = List.class)
    @GetMapping("/jobs")
    List<Job> getAll() {
        return jobRepository.findAll();
    }

    @ApiOperation(value = "Add a job")
    @PostMapping("/jobs")
    Job add(@ApiParam(value = "Job object store in database table") @Valid @RequestBody Job newJob) {
        return jobRepository.save(newJob);
    }

    @ApiOperation(value = "Get a job by Id")
    @GetMapping("/jobs/{id}")
    Job getById(@ApiParam(value = "Job id from which job object will retrieve", required = true, example = "123") @PathVariable Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Job", id));
    }

    @ApiOperation(value = "Update job")
    @PutMapping("/jobs/{id}")
    Job edit(
            @ApiParam(value = "Updated job object",required = true) @Valid @RequestBody Job newJob,
            @ApiParam(value = "Job Id to update job object", required = true, example = "123") @PathVariable Long id) {
        return jobRepository.findById(id)
                .map(job -> {
                    job.setTitle(newJob.getTitle());
                    job.setDescription(newJob.getDescription());
                    job.setRequiredSkills(newJob.getRequiredSkills());
                    return jobRepository.save(job);
                })
                .orElseGet(() -> {
                    newJob.setId(id);
                    return jobRepository.save(newJob);
                });
    }

    @ApiOperation(value = "Delete a job")
    @DeleteMapping("/jobs/{id}")
    void delete(
            @ApiParam(value = "Employee Id from which employee object will delete from database table", required = true, example = "123") @PathVariable Long id) {
        jobRepository.deleteById(id);
    }

    @ApiOperation(value = "Submit job")
    @PostMapping(value = "/job/{jobId}/apply")
    JobSubmission submitJob(@ApiParam(value = "Job Id", required = true, example = "123") @PathVariable Long jobId,
                   @ApiParam(value = "Candidate Object", required = true) @RequestBody Candidate candidate) throws IOException {

        Long fileUploadedId = candidate.getFileUploaded().getId();
        FileUploaded fileUploaded = fileUploadedRepository.findById(fileUploadedId)
                .orElseThrow(() -> new ObjectNotFoundException("FileUploaded", fileUploadedId));
        candidate.setFileUploaded(fileUploaded);
        Candidate newCandidate = candidateRepository.save(candidate);

        JobSubmission jobSubmission = new JobSubmission();
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ObjectNotFoundException("Job", jobId));

        jobSubmission.setJob(job);
        jobSubmission.setCandidate(newCandidate);
        return jobSubmissionRepository.save(jobSubmission);
    }


    @ApiOperation(value = "List Applications")
    @GetMapping(value = "/job/{jobId}/applications")
    List<JobSubmission> getJobSumbission(@ApiParam(value = "Job Id", defaultValue = "1") @PathVariable Long jobId){
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ObjectNotFoundException("Job", jobId));
        return jobSubmissionRepository.findByJob(job);
    }

}
