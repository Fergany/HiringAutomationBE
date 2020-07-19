package com.orange.hiring_automation.api;

import com.orange.hiring_automation.model.Interview;
import com.orange.hiring_automation.repository.InterviewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Interview API", produces = "application/json")
@RestController
public class InterviewController {
    private InterviewRepository interviewRepository;

    public InterviewController(InterviewRepository interviewRepository){
        this.interviewRepository = interviewRepository;
    }

    @ApiOperation(value = "Get all interviews")
    @GetMapping(value = "/interviews")
    List<Interview> getAllInterviews(){
       return this.interviewRepository.findAll();
    }
}
