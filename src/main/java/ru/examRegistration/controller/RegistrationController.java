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

/**
 * Контроллер приложения регистрации студентов со списком их экзаменов
 */
@RestController("/app")
public class RegistrationController {
    /**Поле с сервисом регистрации студентов*/
    @Autowired
    RegistrationServiceImpl service;

    /**
     * Получить информацию по всем зарегистрированным студентам
     * @return возвращыет ResponseEntity со статусом 204 или 200
     */
    @GetMapping("/allStudents")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = service.getAllStudents();
        List<StudentDto> studentDto = service.getAllStudents().stream().map(StudentDto::fromStudent).toList();
        return students.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    /**
     * Получить информацию по конкретному студенту используя его id
     * @param id - индентификацонный номер судента
     * @return ResponseEntity с информацией по студенту в теле сообщения и статусом 200 либо ResponseEntity со статусом 404
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        Student student = service.getStudentById(id);
        return student != null ? new ResponseEntity<>(StudentDto.fromStudent(student), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Добавление нового студента
     * @param dto - тело сообщения с описанием полей нового студента
     * @return возвращает ResponseEntity со статусом 201
     * @throws BadRequestException исключение пробрасывается в случае не корректных данных от пользователя
     */
    @PostMapping("/student")
    public ResponseEntity<Object> createNewStudent(@RequestBody StudentDto dto) throws BadRequestException {
        checkDto(dto);
        service.createNewStudent(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Обновить информацию по существующему пользователю
     * @param id - индентификацонный номер существующего судента
     * @param dto - новая информация по студенту
     * @return ResponseEntity новой информацией по студенту и статусом 200
     * @throws BadRequestException исключение пробрасывается в случае не корректных данных от пользователя
     */
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

    /**
     * Удалить пользователя по его id
     * @param id - индентификацонный номер существующего судента
     * @return ResponseEntity информацией по удалению
     */
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
