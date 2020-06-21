package com.orange.hiring_automation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachine
public class SimpleStateMachineConfiguration
        extends StateMachineConfigurerAdapter<String, String> {

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
            throws Exception {

        states
                .withStates()
                .initial("Job Submission")
                .end("Process Completed")
                .states(
                        new HashSet<>(Arrays.asList("Application Scan", "HR Interview", "Technical Interview")));

    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<String, String> transitions)
            throws Exception {

        transitions.withExternal()
                .source("Job Submission").target("Application Scan").event("Scan").and()
                .withExternal()
                .source("Application Scan").target("HR Interview").event("Interview By HR").and()
                .withExternal()
                .source("HR Interview").target("Technical Interview").event("Interview By Technical").and()
                .withExternal()
                .source("Technical Interview").target("Process Completed").event("end");
    }
}
