package ru.studentRegistration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.studentRegistration.dto.StudentDto;
import ru.studentRegistration.model.Exam;
import ru.studentRegistration.model.Student;
import ru.studentRegistration.repository.StudentRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    private StudentRepository repository;
    @InjectMocks
    private StudentServiceImpl service;

    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student(1,
                "Антон",
                "Рогожин",
                LocalDate.of(1990, 11, 22),
                3,
                List.of(Exam.ENGLISH, Exam.MATH, Exam.PHYSICS));
    }

    @Test
    void getStudents() {
        List<Student> expected = List.of(student);
        given(repository.findAll()).willReturn(expected);
        List<Student> actual = service.getStudents(0, 1);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    void getStudentById() {
        given(repository.findById(1L)).willReturn(Optional.of(student));
        Student actual = service.getStudentById(1L);
        Assertions.assertEquals(student, actual);
    }

    @Test
    void createNewStudent() {
        given(repository.save(student)).willReturn(student);
        Student actual = service.createNewStudent(StudentDto.fromStudent(student));
        Assertions.assertEquals(student, actual);
    }

    @Test
    void updateStudent() {
        long idForUpdate = 1L;
        given(repository.findById(idForUpdate)).willReturn(Optional.of(student));
        given(repository.save(student)).willReturn(student);
        StudentDto dto = new StudentDto(idForUpdate, "Anton", "Rogozhin", LocalDate.of(1990, 11, 22), 3, Collections.emptyList());
        Student actual = service.updateStudent(idForUpdate, dto);
        Assertions.assertEquals(dto.toStudent(), actual);
    }

    @Test
    void deleteStudent() {
        long idForDelete = 1L;
        willDoNothing().given(repository).deleteById(idForDelete);
        service.deleteStudent(idForDelete);
        verify(repository, times(1)).deleteById(idForDelete);
    }
}