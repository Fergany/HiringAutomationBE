package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.ObjectNotFoundException;
import com.orange.hiring_automation.model.JobSubmission;
import com.orange.hiring_automation.repository.JobSubmissionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Job Submission", value = "API for Job Submission")
@RestController
public class JobSubmissionController {
    private JobSubmissionRepository jobSubmissionRepository;

    JobSubmissionController(JobSubmissionRepository jobSubmissionRepository){
        this.jobSubmissionRepository = jobSubmissionRepository;
    }

    @ApiOperation(value = "Get Job Submission by Id")
    @GetMapping(value = "/jobSubmission/{jobSubmissionId}")
    JobSubmission getJobSubmission(@ApiParam(value = "jobSubmissionId", required = true, defaultValue = "1") @PathVariable Long jobSubmissionId){
        return jobSubmissionRepository.findById(jobSubmissionId).orElseThrow(
                () -> new ObjectNotFoundException("Job Submission", jobSubmissionId));
    }
}
