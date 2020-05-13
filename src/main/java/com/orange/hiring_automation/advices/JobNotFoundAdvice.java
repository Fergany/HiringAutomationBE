package com.orange.hiring_automation.advices;

import com.orange.hiring_automation.exceptions.JobNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class JobNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(JobNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(JobNotFoundException ex) {
        return ex.getMessage();
    }
}