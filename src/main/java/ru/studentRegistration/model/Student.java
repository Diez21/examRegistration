package ru.studentRegistration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private String firstName;
    /**
     * Фамилия
     */
    private String lastName;
    /**
     * Дата рождения
     */
    private LocalDate birthDate = LocalDate.now();
    /**
     * Год обучения(Курс)
     */
    private int step;
    /**
     * Сиписок экзаменов студента
     */
    @ElementCollection
    private List<Exam> exams;
}