package com.orange.hiring_automation.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException(Long id) {
        super("Could not find job " + id);
    }
}
