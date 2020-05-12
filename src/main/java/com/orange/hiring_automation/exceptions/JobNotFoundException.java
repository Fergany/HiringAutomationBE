package com.orange.hiring_automation.exceptions;

public class JobNotFoundException extends RuntimeException{
    JobNotFoundException(Long id) {
        super("Could not find job " + id);
    }
}
