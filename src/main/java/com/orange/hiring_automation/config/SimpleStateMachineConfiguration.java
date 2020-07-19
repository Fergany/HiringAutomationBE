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
        extends StateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {

        states
                .withStates()
                .initial(States.JOB_SUBMISSION)
                .end(States.PROCESS_COMPLETED)
                .states(
                        new HashSet<>(Arrays.asList(States.APPLICATION_SCAN, States.CANDIDATE_ASSESSMENT, States.INTERVIEW_PROCESS_STARTED, States.HR_INTERVIEW, States.TECHNICAL_INTERVIEW)));

    }

    @Override
    public void configure(
            StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {

        transitions.withExternal()
                .source(States.JOB_SUBMISSION).target(States.APPLICATION_SCAN).event(Events.APPLICATION_SCAN).and()
                .withExternal()
                .source(States.APPLICATION_SCAN).target(States.CANDIDATE_ASSESSMENT).event(Events.CANDIDATE_ASSESSMENT).and()
                .withExternal()
                .source(States.CANDIDATE_ASSESSMENT).target(States.INTERVIEW_PROCESS_STARTED).event(Events.START_INTERVIEW_PROCESS).and()
                .withExternal()
                .source(States.APPLICATION_SCAN).target(States.HR_INTERVIEW).event(Events.HR_INTERVIEW).and()
                .withExternal()
                .source(States.HR_INTERVIEW).target(States.TECHNICAL_INTERVIEW).event(Events.TECHNICAL_INTERVIEW).and()
                .withExternal()
                .source(States.TECHNICAL_INTERVIEW).target(States.PROCESS_COMPLETED).event(Events.END_THE_PROCESS);
    }
}

