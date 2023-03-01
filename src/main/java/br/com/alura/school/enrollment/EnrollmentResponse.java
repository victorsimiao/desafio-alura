package br.com.alura.school.enrollment;

public class EnrollmentResponse {

    private String email;

    private long quantidadeMatriculas;

    public EnrollmentResponse(String email, long quantidadeMatricula) {
        this.email = email;
        this.quantidadeMatriculas = quantidadeMatricula;
    }


    public String getEmail() {
        return email;
    }

    public long getQuantidadeMatriculas() {
        return quantidadeMatriculas;
    }
}
