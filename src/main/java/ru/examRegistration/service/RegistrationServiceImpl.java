package ru.examRegistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.examRegistration.dto.StudentDto;
import ru.examRegistration.exception.ResourceNotFoundException;
import ru.examRegistration.model.Student;
import ru.examRegistration.repository.StudentRepository;

import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    StudentRepository repository;

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));
    }

    @Override
    public void createNewStudent(StudentDto dto) {
        Student student = dto.toStudent();
        repository.save(student);
    }

    @Override
    public void updateStudent(long id, StudentDto dto) {
        Student updateStudent = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + id));
        updateStudent.setFirstName(dto.getFirstName());
        updateStudent.setLastName(dto.getLastName());
        updateStudent.setBirthDate(dto.getBirthDate());
        updateStudent.setStep(dto.getStep());
        updateStudent.setExams(dto.getExams());
        repository.save(updateStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}
