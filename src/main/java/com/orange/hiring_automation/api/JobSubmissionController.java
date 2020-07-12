package com.orange.hiring_automation.api;

import com.orange.hiring_automation.config.*;
import com.orange.hiring_automation.exceptions.ObjectNotFoundException;
import com.orange.hiring_automation.model.JobSubmission;
import com.orange.hiring_automation.repository.JobSubmissionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Job Submission", value = "API for Job Submission")
@RestController
public class JobSubmissionController {
    private final JobSubmissionRepository jobSubmissionRepository;
    private final StateMachine<States, Events> stateMachine;

    JobSubmissionController(JobSubmissionRepository jobSubmissionRepository, StateMachine<States, Events> stateMachine){
        this.jobSubmissionRepository = jobSubmissionRepository;
        this.stateMachine = stateMachine;
    }

    @ApiOperation(value = "Get Job Submission by Id")
    @GetMapping(value = "/jobSubmission/{id}")
    JobSubmission getJobSubmission(@ApiParam(value = "jobSubmissionId", required = true, defaultValue = "1") @PathVariable Long id){
        stateMachine.sendEvent(Events.APPLICATION_SCAN);
        return jobSubmissionRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Job Submission", id));
    }
}
