package com.orange.hiring_automation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "assessments")
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assessment_id")
    Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    Exam exam;

    @Column(name = "score")
    double score;

    @Column(name = "candidate_feedback", length = 5000)
    private String candidateFeedback;

    @Column(name = "interviewer_feedback", length = 5000)
    private String interviewerFeedback;

    @Column(name = "sent_at", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date sentAt;

    @Column(name = "submitted_at")
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date submittedAt;
}
