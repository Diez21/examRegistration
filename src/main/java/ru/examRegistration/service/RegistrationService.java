package ru.examRegistration.service;

import ru.examRegistration.dto.StudentDto;
import ru.examRegistration.model.Student;

import java.util.List;

public interface RegistrationService {
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void createNewStudent(StudentDto dto);

    void updateStudent(long id, StudentDto dto);

    void deleteStudent(Long id);
}
