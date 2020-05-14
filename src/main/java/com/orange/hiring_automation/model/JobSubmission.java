package com.orange.hiring_automation.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "job_submissions")
public class JobSubmission {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "submitted_at")
    private Date submittedAt;
}
