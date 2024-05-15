package ru.examRegistration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

/**
 * Базовый класс студента
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;
    /**Имя*/
    private String firstName;
    /**Фамилия*/
    private String lastName;
    /**Дата рождения*/
    private LocalDate birthDate;
    /**Год обучения(Курс)*/
    private int step;
    /**Сиписок экзаменов студента*/
    private List<Exam> exams;
}
