package br.com.alura.school.enrollment;

import javax.validation.constraints.NotBlank;

public class EnrollmentRequest {

    @NotBlank
    private String username;

    public EnrollmentRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
