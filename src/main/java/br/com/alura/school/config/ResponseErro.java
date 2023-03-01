package br.com.alura.school.config;

public class ResponseErro {

    private Integer code;
    private String status;
    private String message;

    public ResponseErro(Integer code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
