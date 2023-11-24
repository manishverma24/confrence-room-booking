package com.example.confrenceroombooking.exception;

import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(UserHandledException.class)
    protected ResponseEntity<Object> handleUserException(UserHandledException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    private static class ErrorResponse {
        private String message;
        private HttpStatus httpStatus;

        public ErrorResponse(String message, HttpStatus httpStatus) {
            this.message = message;
            this.httpStatus = httpStatus;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public void setHttpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
        }
    }
}
