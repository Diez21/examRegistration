package ru.examRegistration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "STUDENT")
@Entity
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
//    private Date birthDate;
    private int step;
    @ElementCollection
    private List<Exam> exams;
}
