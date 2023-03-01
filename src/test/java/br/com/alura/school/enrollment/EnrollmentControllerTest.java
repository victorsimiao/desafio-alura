package br.com.alura.school.enrollment;

import br.com.alura.school.course.Course;
import br.com.alura.school.course.CourseRepository;
import br.com.alura.school.user.User;
import br.com.alura.school.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "classpath:schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EnrollmentControllerTest {


    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentService enrollmentService;


    @Test
    void should_retrieve_all_enrollment() throws Exception {
        Course course = courseRepository.save(new Course("tdd-java", "TDD e Java", "Testes automatizados com JUnit"));
        User userDev = userRepository.save(new User("Victor", "victor@hotmail.com"));
        User userQA = userRepository.save(new User("Jonas", "jonas@hotmail.com"));
        enrollmentService.newEnrollment(new Enrollment(course, userDev));
        enrollmentService.newEnrollment(new Enrollment(course, userQA));

        mockMvc.perform(get("/courses/enroll/report")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].email", is("victor@hotmail.com")))
                .andExpect(jsonPath("$[0].quantidadeMatriculas", is(1)))
                .andExpect(jsonPath("$[1].email", is("jonas@hotmail.com")))
                .andExpect(jsonPath("$[1].quantidadeMatriculas", is(1)));

    }


    @Test
    void should_return_no_content_when_enrollment_does_not_exist() throws Exception {
        mockMvc.perform(get("/courses/enroll/report")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}