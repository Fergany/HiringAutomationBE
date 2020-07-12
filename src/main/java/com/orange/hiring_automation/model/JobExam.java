package com.orange.hiring_automation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "job_exams")
public class JobExam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
}
