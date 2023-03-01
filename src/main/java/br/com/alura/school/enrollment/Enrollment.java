package br.com.alura.school.enrollment;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private OffsetDateTime createdAt;

    @Deprecated
    public Enrollment() {
    }

    public Enrollment(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(createdAt)) {
            createdAt = OffsetDateTime.now(ZoneOffset.of("-03:00"));
        }
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public User getUser() {
        return user;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }
}
