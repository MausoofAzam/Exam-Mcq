package com.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionType;
    @OneToOne
    private Option options;
    private Integer totalMarks;
    private String category;
    private String level;
    private String questionDescription;
    @Transient
    private Boolean isCorrectAnswer;
}