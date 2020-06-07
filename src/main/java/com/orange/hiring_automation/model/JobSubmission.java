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
@Table(name = "job_submissions")
public class JobSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(name = "submitted_at", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(pattern="MM/dd/yyyy")
    private Date submittedAt;
}
