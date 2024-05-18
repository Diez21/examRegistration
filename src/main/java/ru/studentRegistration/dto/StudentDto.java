package ru.studentRegistration.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.studentRegistration.model.Exam;
import ru.studentRegistration.model.Student;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class StudentDto {
    private long id;
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String firstName;
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String lastName;
    @Past
    private LocalDate birthDate;
    @Min(1)
    private int step;
    @NotNull
    private List<Exam> exams;

    public Student toStudent() {
        return new Student(id, firstName, lastName, birthDate, step, exams);
    }

    public static StudentDto fromStudent(Student student) {
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName(), student.getBirthDate(), student.getStep(), student.getExams());
    }
}