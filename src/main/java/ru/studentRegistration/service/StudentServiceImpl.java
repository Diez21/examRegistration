package ru.studentRegistration.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.studentRegistration.dto.StudentDto;
import ru.studentRegistration.exception.ResourceNotFoundException;
import ru.studentRegistration.model.Student;
import ru.studentRegistration.repository.StudentRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(
            StudentServiceImpl.class);
    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getStudents(Integer skip, Integer size) {
        return repository.findAll().subList(skip, size);
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));
    }

    @Override
    public Student createNewStudent(StudentDto dto) {
        Student newStudent = repository.save(dto.toStudent());
        logger.debug("Added new student: {}", newStudent);
        return newStudent;
    }

    @Override
    @Transactional
    public Student updateStudent(long id, StudentDto dto) {
        Student studentForUpdate = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));
        studentForUpdate.setFirstName(dto.getFirstName());
        studentForUpdate.setLastName(dto.getLastName());
        studentForUpdate.setBirthDate(dto.getBirthDate());
        studentForUpdate.setStep(dto.getStep());
        studentForUpdate.setExams(dto.getExams());
        Student updatedStudent = repository.save(studentForUpdate);
        logger.debug("Student with id {} updated. New student info: {}", id, updatedStudent);
        return updatedStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
        logger.debug("Student with id {} deleted.", id);
    }
}