package ru.examRegistration.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.examRegistration.dto.StudentDto;
import ru.examRegistration.model.Student;
import ru.examRegistration.service.RegistrationServiceImpl;

import java.util.List;

@RestController("/api")
public class RegistrationController {
    @Autowired
    RegistrationServiceImpl service;

    @GetMapping("/allStudents")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = service.getAllStudents();
        List<StudentDto> studentDto = service.getAllStudents().stream().map(StudentDto::fromStudent).toList();
        return students.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        Student student = service.getStudentById(id);
        return student != null ? new ResponseEntity<>(StudentDto.fromStudent(student), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/student")
    public ResponseEntity<Object> createNewStudent(@RequestBody StudentDto dto) throws BadRequestException {
        checkDto(dto);
        service.createNewStudent(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable long id, @RequestBody StudentDto dto) throws BadRequestException {
        if (id <= 0) {
            throw new BadRequestException("id не может быть меньше 1");
        }
        checkDto(dto);
        dto.setId(id);
        service.updateStudent(id, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        service.deleteStudent(id);
        return new ResponseEntity<>("Студент с id " + id + " успешно удален", HttpStatus.OK);
    }

    private void checkDto(StudentDto dto) throws BadRequestException {
        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            throw new BadRequestException("FirstName не может ровняться null или быть пустым");
        }
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
            throw new BadRequestException("LastName не может ровняться null или быть пустым");
        }
        if (dto.getBirthDate() == null) {
            throw new BadRequestException("BirthDate не может ровняться null");
        }
        if (dto.getStep() <= 0) {
            throw new BadRequestException("Step не может быть меньше 1");
        }
    }
}
