package com.orange.hiring_automation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "interview_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @ManyToOne
    @JoinColumn(name = "interviewer_id")
    User interviewer;

    @Column(name = "interviewer_feedback", length = 5000)
    private String interviewerFeedback;
}
