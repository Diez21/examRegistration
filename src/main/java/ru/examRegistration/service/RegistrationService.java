package ru.examRegistration.service;

import ru.examRegistration.dto.StudentDto;
import ru.examRegistration.model.Student;

import java.util.List;

/**
 * Сервис для обработки поступающих от контроллера запросов
 */
public interface RegistrationService {
    /**
     * Получить информацию по всем зарегистрированным студентам
     * @return List<Student> список судентов
     */
    List<Student> getAllStudents();

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
    void createNewStudent(StudentDto dto);

    /**
     * Обновить информацию по студенту, используя его id
     * @param id - индентификацонный номер существующего судента
     * @param dto - новая информация о студенте
     */
    void updateStudent(long id, StudentDto dto);

    /**
     * Удалить существующего студента, используя его id
     * @param id - индентификацонный номер существующего судента
     */
    void deleteStudent(Long id);
}
