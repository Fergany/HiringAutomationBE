package com.orange.hiring_automation.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Data
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "job_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "required_skills", length = 5000)
    private String requiredSkills;

//    @OneToMany(mappedBy = "job")
//    private Set<JobSubmission> submissions;

    public Job(){
    }

    public Job(String title, String description, String requiredSkills){
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

}