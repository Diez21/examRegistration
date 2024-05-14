package ru.examRegistration.service;

import ru.examRegistration.model.Student;

import java.util.List;

public interface RegistrationService {
    List<Student> getAllStudents();

    Student getStudentById(Long id);

    void createNewStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Long id);
}
