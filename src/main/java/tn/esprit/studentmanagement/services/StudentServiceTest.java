package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.entities.Status;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldReturnAllStudents() {
        Student s1 = createStudent(1L, "Haythem");
        Student s2 = createStudent(2L, "Ali");

        when(studentRepository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Student> result = studentService.getAllStudents();

        assertThat(result).hasSize(2);
        verify(studentRepository).findAll();
    }

    @Test
    void shouldFindStudentById() {
        Student student = createStudent(1L, "Haythem");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student found = studentService.getStudentById(1L);
        assertThat(found.getFirstName()).isEqualTo("Haythem");
    }

    private Student createStudent(Long id, String firstName) {
        Student s = new Student();
        s.setId(id);
        s.setFirstName(firstName);
        s.setLastName("Test");
        s.setEmail(firstName.toLowerCase() + "@test.com");
        s.setDateOfBirth(LocalDate.of(2000, 1, 1));
        s.setStatus(Status.ACTIVE);
        return s;
    }
}