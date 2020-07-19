package com.orange.hiring_automation;

import com.orange.hiring_automation.model.Exam;
import com.orange.hiring_automation.model.Job;
import com.orange.hiring_automation.model.JobExam;
import com.orange.hiring_automation.model.UserRole;
import com.orange.hiring_automation.repository.ExamRepository;
import com.orange.hiring_automation.repository.JobExamRepository;
import com.orange.hiring_automation.repository.JobRepository;
import com.orange.hiring_automation.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(JobRepository jobRepository,
                                   ExamRepository examRepository,
                                   JobExamRepository jobExamRepository,
                                   UserRoleRepository userRoleRepository) {
        return args -> {
            Job sofwareEngineerJob = jobRepository.save(new Job("Software Engineer",
                    "Design and implement our PaaS eCommerce backend in Scala as well as our core infrastructure components like database mappings and web service APIs for high availability e-commerce\n" +
                            "Analyse product requirements and discuss technical approaches\n" +
                            "Test software components regarding usability, functionality and performance and work closely with Product Management and DevOps\n" +
                            "Taking part in on-call rotation (with the worldwide distributed team) for production systems",
                    "Bachelor's degree (or higher) in Computer Science and/or equivalent experience/qualification and at least 4 years experience as Software Engineer + applied work experience in Scala\n" +
                            "Fluent English for work in international teams\n" +
                            "Deep insights in functional programming and practical work experience with Scala and in developing server-side systems using the JVM\n" +
                            "Solid understanding of parallel and asynchronous programming as well as non-blocking I/O\n" +
                            "Experience in developing REST APIs and knowledge of scalable architectures (incl. sharding, replication, load balancing and fail over)\n" +
                            "Aspiration to constantly improve yourself and learn new technologies, concepts, etc.\n" +
                            "Great team player & nice colleague who enjoys our working & company culture"));
            log.info("Preloading " + sofwareEngineerJob);

            Job businessDevelopmentManagerJob = jobRepository.save(new Job("Business development manager",
                    "Support of our team head in managing our partner accounts\n" +
                            "Support and independent acquisition of new cooperation and sales partners in Europe\n" +
                            "Support for existing customers and new partners from the e-commerce sector\n" +
                            "Generation of cross and upselling potential via partner accounts\n" +
                            "Continuous market observation of current e-commerce developments and the derivation of recommendations for action and strategies\n" +
                            "Supporting our marketing activities and participating in trade fairs and events to maintain and expand business relationships\n" +
                            "Provision of analyzes, reports and presentations",
                    "Completed studies in one of the following fields: business administration / economics, business informatics or similar, relevant training\n" +
                            "At least three years of professional experience in business development or partner management in e-commerce or software (experience with digital agencies would be a big plus)\n" +
                            "Safe handling of MS Office or iWorks as well as Salesforce or similar. CRM tools\n" +
                            "Very good knowledge of German (at mother tongue level) and business fluent English\n" +
                            "Confident manner and high customer orientation\n" +
                            "Very good analytical, communication and presentation skills\n" +
                            "Structured work and the ability to work on different tasks / deadlines / stakeholders in parallel\n" +
                            "Team player who would like to work in a tech company together with really good colleagues in an international environment"));

            log.info("Preloading " + businessDevelopmentManagerJob);

            Exam exam = examRepository.save(new Exam("https://leetcode.com/explore/featured/card/30-day-leetcoding-challenge/528/week-1/"));

            log.info("Preloading " + exam);

            JobExam jobExam = new JobExam();
            jobExam.setJob(sofwareEngineerJob);
            jobExam.setExam(exam);
            jobExamRepository.save(jobExam);
            log.info("Preloading " + jobExam);

            UserRole technicalInterviewerUserRole =  userRoleRepository.save( new UserRole("TECHNICAL_INTERVIEWER"));
            UserRole HRInterviewerUserRole =  userRoleRepository.save( new UserRole("HR_INTERVIEWER"));

            User
        };
    }
}