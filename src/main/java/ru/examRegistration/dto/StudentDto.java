package ru.examRegistration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.examRegistration.model.Exam;
import ru.examRegistration.model.Student;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private long id = 0;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int step;
    private List<Exam> exams = Collections.emptyList();

    public Student toStudent() {
        return new Student(id, firstName, lastName, birthDate, step, exams);
    }

    public static StudentDto fromStudent(Student student) {
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName(), student.getBirthDate(), student.getStep(), student.getExams());
    }
}
