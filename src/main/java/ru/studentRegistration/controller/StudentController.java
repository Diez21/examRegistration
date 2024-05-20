package ru.studentRegistration.controller;

import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.studentRegistration.dto.StudentDto;
import ru.studentRegistration.model.Student;
import ru.studentRegistration.service.StudentService;

import java.util.List;

/**
 * Контроллер приложения регистрации студентов со списком их экзаменов
 */
@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService service;

    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getStudents(@RequestParam @Min(0) Integer skip, @RequestParam @Min(1) Integer size) {
        List<Student> students = service.getStudents(skip, size);
        List<StudentDto> studentsDto = students.stream().map(StudentDto::fromStudent).toList();
        return students.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(studentsDto, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable @Min(1) Long id) {
        Student student = service.getStudentById(id);
        return student != null ? new ResponseEntity<>(StudentDto.fromStudent(student), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewStudent(@RequestBody StudentDto dto) {
        StudentDto.fromStudent(service.createNewStudent(dto));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable @Min(1) long id, @RequestBody StudentDto dto) {
        dto.setId(id);
        service.updateStudent(id, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentById(@PathVariable @Min(1) Long id) {
        service.deleteStudent(id);
    }
}
