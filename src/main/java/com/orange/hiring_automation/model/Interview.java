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
    Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    Candidate candidate;

    @OneToOne
    @JoinColumn(name = "assessment_id")
    Assessment assessment;

    @Column(name = "interviewer_feedback", length = 5000)
    String interviewerFeedback;
}
