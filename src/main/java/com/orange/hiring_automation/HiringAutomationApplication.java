package com.orange.hiring_automation;

import com.orange.hiring_automation.model.Job;
import com.orange.hiring_automation.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class HiringAutomationApplication {
	public static void main(String[] args) {
        SpringApplication.run(HiringAutomationApplication.class, args);
    }
}
