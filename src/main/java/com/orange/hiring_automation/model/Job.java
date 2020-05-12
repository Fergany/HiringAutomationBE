package com.orange.hiring_automation.model;

import javax.persistence.*;
import lombok.Data;

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

    @Column(name = "description")
    private String description;

    @Column(name = "required_skills")
    private String requiredSkills;

    @Override
    public String toString() {
        return String.format(
                "Job[id=%d, title='%s', description='%s, required_skills=%s']",
                id, title, description, requiredSkills);
    }

    public Job(){
    }

    public Job(String title, String description, String requiredSkills){
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
    }

}