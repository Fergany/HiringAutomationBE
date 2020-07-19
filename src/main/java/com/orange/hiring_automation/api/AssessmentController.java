package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.ObjectNotFoundException;
import com.orange.hiring_automation.model.*;
import com.orange.hiring_automation.repository.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Assessment API", value = "Assessment API ...", produces = "application/json")
@RestController
public class AssessmentController {
    private AssessmentRepository assessmentRepository;
    private JobExamRepository jobExamRepository;
    private JobSubmissionRepository jobSubmissionRepository;
    private InterviewRepository interviewRepository;

    AssessmentController(AssessmentRepository assessmentRepository,
                         JobSubmissionRepository jobSubmissionRepository,
                         JobExamRepository jobExamRepository,
                         InterviewRepository interviewRepository) {
        this.assessmentRepository = assessmentRepository;
        this.jobSubmissionRepository = jobSubmissionRepository;
        this.jobExamRepository = jobExamRepository;
        this.interviewRepository = interviewRepository;
    }

    @ApiOperation(value = "Save Assessment")
    @PostMapping(value = "/assessments/jobSubmission/{jobSubmissionId}")
    Assessment save(@ApiParam(value = "jobSubmission Id", defaultValue = "7") @PathVariable Long jobSubmissionId) {
        JobSubmission jobSubmission = jobSubmissionRepository.findById(jobSubmissionId)
                .orElseThrow(() -> new ObjectNotFoundException("JobSubmission", jobSubmissionId));
        Job job = jobSubmission.getJob();
        Candidate candidate = jobSubmission.getCandidate();
        JobExam jobExam = jobExamRepository.findByJob(job);
        Exam exam = jobExam.getExam();
        Assessment assessment = new Assessment();
        assessment.setCandidate(candidate);
        assessment.setExam(exam);
        Assessment newAssessment = assessmentRepository.save(assessment);

        Interview  interview = new Interview();
        interview.setCandidate(candidate);
        interview.setAssessment(newAssessment);
        interviewRepository.save(interview);
        return newAssessment;
    }

    @ApiOperation(value = "Update Assessment")
    @PutMapping(value = "/assessments")
    Assessment update(@ApiParam(value="Assessment Object") @RequestBody Assessment assessment) {
        Long id = assessment.getId();
        Assessment updatedAssessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Assessment", id));
        updatedAssessment.setSubmittedAt(assessment.getSubmittedAt() != null? assessment.getSubmittedAt() : updatedAssessment.getSubmittedAt());
        updatedAssessment.setScore(assessment.getScore() != 0? assessment.getScore() : updatedAssessment.getScore());
        updatedAssessment.setCandidateFeedback(assessment.getCandidateFeedback() != null? assessment.getCandidateFeedback() : updatedAssessment.getCandidateFeedback());
        updatedAssessment.setInterviewerFeedback(assessment.getInterviewerFeedback() != null? assessment.getInterviewerFeedback() : updatedAssessment.getInterviewerFeedback());
        return assessmentRepository.save(updatedAssessment);
    }

    @ApiOperation(value = "List Assessments")
    @GetMapping(value = "/assessments")
    List<Assessment> getAllAssessments(){
        return assessmentRepository.findAll();
    }
}
