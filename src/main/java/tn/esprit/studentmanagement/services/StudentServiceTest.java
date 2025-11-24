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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceUnitTest {

    @Mock private StudentRepository studentRepository;
    @InjectMocks private StudentService studentService;

    @Test void getAllStudents_ReturnsList() {
        when(studentRepository.findAll()).thenReturn(Arrays.asList(createStudent(1L), createStudent(2L)));
        assertThat(studentService.getAllStudents()).hasSize(2);
        verify(studentRepository).findAll();
    }

    @Test void getStudentById_ExistingId_ReturnsStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(createStudent(1L)));
        assertThat(studentService.getStudentById(1L)).isNotNull();
    }

    @Test void saveStudent_CallsRepositorySave() {
        Student student = createStudent(null);
        when(studentRepository.save(any())).thenReturn(student);
        studentService.saveStudent(student);
        verify(studentRepository).save(student);
    }

    @Test void updateStudent_ExistingId_UpdatesAndReturns() {
        Student existing = createStudent(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(studentRepository.save(any())).thenReturn(existing);
        Student updated = studentService.saveStudent(existing);
        assertThat(updated.getIdStudent()).isEqualTo(1L);
    }

    @Test void deleteStudent_CallsRepositoryDelete() {
        studentService.deleteStudent(99L);
        verify(studentRepository).deleteById(99L);
    }

    private Student createStudent(Long id) {
        Student s = new Student();
        s.setId(id);
        s.setFirstName("Test");
        s.setLastName("User");
        s.setEmail("test@test.com");
        s.setDateOfBirth(LocalDate.of(2000, 1, 1));
        s.setStatus(Status.ACTIVE);
        return s;
    }
}