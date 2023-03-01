package br.com.alura.school.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    @Query("SELECT enroll"
            + " FROM Enrollment enroll"
            + " WHERE enroll.course.id = :courseId"
            + " AND enroll.user.id = :userId")
    Optional<Enrollment> findByExistingEnrollment(Long courseId, Long userId);

    @Query(value = "SELECT " +
            "new br.com.alura.school.enrollment.EnrollmentResponse(enroll.user.email, COUNT(enroll.user))" +
            " FROM Enrollment enroll" +
            " GROUP BY enroll.user" +
            " ORDER BY COUNT(enroll.user) DESC")
    List<EnrollmentResponse> findByErollment();
}
