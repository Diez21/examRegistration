package ru.examRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.examRegistration.model.Student;

/**
 * Репозиторий для работы с базой данных, в которой находится таблица студентов
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
