package br.com.alura.school.enrollment;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class EnrollmentController {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentService enrollmentService;
    private final EnrollmentRepository enrollmentRepository;


    public EnrollmentController(CourseRepository courseRepository, UserRepository userRepository, EnrollmentService enrollmentService, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.enrollmentService = enrollmentService;
        this.enrollmentRepository = enrollmentRepository;
    }

    @PostMapping("/courses/{courseCode}/enroll")
    public ResponseEntity<Void> newEnrollment(@RequestBody @Valid EnrollmentRequest enrollmentRequest, @PathVariable("courseCode") String courseCode) {
        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", courseCode)));

        User user = userRepository.findByUsername(enrollmentRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User %s not found", enrollmentRequest.getUsername())));

        enrollmentService.newEnrollment(new Enrollment(course, user));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/courses/enroll/report")
    public ResponseEntity<List<EnrollmentResponse>> findEnroll(){
        return enrollmentRepository.findByErollment().isEmpty() ?
                ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.OK).body(enrollmentRepository.findByErollment());
    }


}
