package ru.studentRegistration.model;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

/**
 * Базовый класс студента
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;
    /**
     * Имя
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * Фамилия
     */
    @Column(name = "last_name")
    private String lastName;
    /**
     * Дата рождения
     */
    @Column(name = "birth_date")
    private LocalDate birthDate = LocalDate.now();
    /**
     * Год обучения(Курс)
     */
    @Column(name = "step")
    private int step;
    /**
     * Сиписок экзаменов студента
     */
    @Column(name = "exams")
    @ElementCollection
    private List<Exam> exams;
}