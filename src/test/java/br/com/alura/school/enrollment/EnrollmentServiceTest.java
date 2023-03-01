package br.com.alura.school.enrollment;

import br.com.alura.school.course.Course;
import br.com.alura.school.exception.InvalidRequestException;
import br.com.alura.school.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Mock
    private EnrollmentRepository enrollmentRepository;


    @Test
    void should_add_new_enrollment(){
        Enrollment enrollment = newEnrollment();
        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        Enrollment victim = enrollmentService.newEnrollment(enrollment);

        assertThat(victim).isEqualTo(enrollment);
    }

    @Test
    void should_throws_exception_when_invalid_enrollment(){
        Enrollment enrollment = newEnrollment();
        when(enrollmentRepository.findByExistingEnrollment(enrollment.getCourse().getId(), enrollment.getUser().getId())).thenReturn(Optional.of(enrollment));

        assertThatThrownBy(() -> enrollmentService.newEnrollment(enrollment)).isInstanceOf(InvalidRequestException.class);

    }


     private Enrollment newEnrollment(){
        Course course = new Course("spring-1", "Spring Basics", "Spring Core and Spring MVC.");
         User user = new User("Victor", "victor@email.com");

        return new Enrollment(course,user);
    }
}