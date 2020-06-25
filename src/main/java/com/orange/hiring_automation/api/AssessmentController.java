package com.orange.hiring_automation.api;

import com.orange.hiring_automation.model.Assessment;
import com.orange.hiring_automation.repository.AssessmentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Assessment API", value = "Assessment API ...", produces = "application/json")
@RestController
public class AssessmentController {
    private AssessmentRepository assessmentRepository;

    AssessmentController(AssessmentRepository assessmentRepository){
        this.assessmentRepository = assessmentRepository;
    }

    @ApiOperation(value = "Save Assessment")
    @PostMapping(value = "/assessments")
    Assessment save(@ApiParam(value = "Assessment Object") @RequestBody Assessment assessment){
        return assessmentRepository.save(assessment);
    }
}
