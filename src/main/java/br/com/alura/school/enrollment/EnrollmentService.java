package br.com.alura.school.enrollment;

import br.com.alura.school.exception.InvalidRequestException;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {


    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment newEnrollment(Enrollment enrollment) {

        if (!enrollmentIsValid(enrollment.getCourse().getId(), enrollment.getUser().getId())) {
            throw new InvalidRequestException("User registered in this course");
        }

        return enrollmentRepository.save(enrollment);
    }

    private Boolean enrollmentIsValid(Long courseId, Long userId) {
        return enrollmentRepository.findByExistingEnrollment(courseId, userId).isEmpty();
    }

}
