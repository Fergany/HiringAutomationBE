package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.ObjectNotFoundException;
import com.orange.hiring_automation.model.*;
import com.orange.hiring_automation.repository.AssessmentRepository;
import com.orange.hiring_automation.repository.CandidateRepository;
import com.orange.hiring_automation.repository.JobExamRepository;
import com.orange.hiring_automation.repository.JobSubmissionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Assessment API", value = "Assessment API ...", produces = "application/json")
@RestController
public class AssessmentController {
    private AssessmentRepository assessmentRepository;
    private JobExamRepository jobExamRepository;
    private JobSubmissionRepository jobSubmissionRepository;

    AssessmentController(AssessmentRepository assessmentRepository,
                         JobSubmissionRepository jobSubmissionRepository,
                         JobExamRepository jobExamRepository) {
        this.assessmentRepository = assessmentRepository;
        this.jobSubmissionRepository = jobSubmissionRepository;
        this.jobExamRepository = jobExamRepository;
    }

    @ApiOperation(value = "Save Assessment")
    @PostMapping(value = "/assessments/jobSubmission/{id}")
    Assessment save(@ApiParam(value = "jobSubmission Id", defaultValue = "7") @PathVariable Long id) {
        JobSubmission jobSubmission = jobSubmissionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("JobSubmission", id));
        Job job = jobSubmission.getJob();
        Candidate candidate = jobSubmission.getCandidate();
        JobExam jobExam = jobExamRepository.findByJob(job);
        Exam exam = jobExam.getExam();
        Assessment assessment = new Assessment();
        assessment.setCandidate(candidate);
        assessment.setExam(exam);
        return assessmentRepository.save(assessment);
    }
}
