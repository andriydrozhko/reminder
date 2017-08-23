package com.reminder.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ExceptionMessageResult {

    public ExceptionMessageResult(String message, HttpStatus status, Map<String, String> errorMap, Integer code) {
        this.message = message;
        this.status = status;
        this.errorMap = errorMap;
        this.code = code;
    }

    private String message;
    private Integer code;
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private Map<String, String> errorMap;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
