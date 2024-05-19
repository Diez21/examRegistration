package ru.studentRegistration.service;

import ru.studentRegistration.model.Student;
import ru.studentRegistration.dto.StudentDto;

import java.util.List;

/**
 * Сервис для обработки поступающих от контроллера запросов
 */
public interface RegistrationService {
    /**
     * Получить информацию по всем зарегистрированным студентам
     * @return List<Student> список судентов
     */
    List<Student> getStudents(Long size);

    /**
     *Получить информацию по конкретному студенту, используя его id
     * @param id - индентификацонный номер существующего судента
     * @return Student информация по студенту
     */
    Student getStudentById(Long id);

    /**
     * Добавить нового студента
     * @param dto информация по новому студенту
     */
    Student createNewStudent(StudentDto dto);

    /**
     * Обновить информацию по студенту, используя его id
     * @param id - индентификацонный номер существующего судента
     * @param dto - новая информация о студенте
     */
    Student updateStudent(long id, StudentDto dto);

    /**
     * Удалить существующего студента, используя его id
     * @param id - индентификацонный номер существующего судента
     */
    void deleteStudent(Long id);
}