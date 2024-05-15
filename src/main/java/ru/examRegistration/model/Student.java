package ru.examRegistration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int step;
    private List<Exam> exams;
}
