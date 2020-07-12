package com.orange.hiring_automation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exam_id")
    Long id;

    @Column(name = "exam_link", length = 500)
    private String examLink;

    public Exam(){
    }

    public Exam(String examLink){
        this.examLink = examLink;
    }
}
