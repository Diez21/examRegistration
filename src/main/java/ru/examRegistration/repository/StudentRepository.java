package ru.examRegistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.examRegistration.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
