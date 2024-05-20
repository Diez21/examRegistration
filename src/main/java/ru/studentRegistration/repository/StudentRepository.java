package ru.studentRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentRegistration.model.Student;

/**
 * Репозиторий для работы с базой данных, в которой находится таблица студентов
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}