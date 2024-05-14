package ru.examRegistration.controller;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.examRegistration.dto.StudentResponse;
import ru.examRegistration.model.Student;
import ru.examRegistration.service.RegistrationServiceImpl;

import java.util.List;

@RestController("/api")
public class RegistrationController {
    @Autowired
    RegistrationServiceImpl service;

    @GetMapping("/allStudents")
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PostMapping("/student")
    public void createNewStudent(@RequestBody Student student) {
        service.createNewStudent(student);
    }

    @PutMapping("/student/{id}")
    public void updateStudentById(@PathVariable Long id, @RequestBody Student updatedStudent) {
        val student = service.getStudentById(id);
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
//        student.setBirthDate(updatedStudent.getBirthDate());
        student.setStep(updatedStudent.getStep());
        student.setExams(updatedStudent.getExams());
        service.updateStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}
