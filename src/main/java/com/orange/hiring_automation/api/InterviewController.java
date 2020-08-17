package com.orange.hiring_automation.api;

import com.orange.hiring_automation.exceptions.ObjectNotFoundException;
import com.orange.hiring_automation.model.Interview;
import com.orange.hiring_automation.model.User;
import com.orange.hiring_automation.repository.InterviewRepository;
import com.orange.hiring_automation.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Interview API", produces = "application/json")
@RestController
public class InterviewController {
    private InterviewRepository interviewRepository;
    private UserRepository userRepository;

    public InterviewController(InterviewRepository interviewRepository,
                               UserRepository userRepository){
        this.interviewRepository = interviewRepository;
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Get all interviews")
    @GetMapping(value = "/interviews")
    List<Interview> getAllInterviews(){
       return this.interviewRepository.findAll();
    }

    @ApiOperation(value = "Update interview")
    @PostMapping(value = "/interviews/{interviewId}/{interviewerId}")
    Interview assignInterviewer(@ApiParam(value = "Interview id", defaultValue = "123") @PathVariable Long interviewId,
                                @ApiParam(value = "Interviewer id", defaultValue = "123") @PathVariable Long interviewerId){
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new ObjectNotFoundException("Interview", interviewId));
        User interviewer = userRepository.findById(interviewerId)
                .orElseThrow(() -> new ObjectNotFoundException("User", interviewerId));
        interview.setInterviewer(interviewer);
        return interviewRepository.save(interview);
    }
}
