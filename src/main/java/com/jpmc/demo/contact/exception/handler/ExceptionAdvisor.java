package com.jpmc.demo.contact.exception.handler;


import com.jpmc.demo.contact.exception.ContactException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler({ContactException.class})
    protected ResponseEntity<Object> handleContactException(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return new ResponseEntity(ex.getMessage(), ((ContactException)ex).getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleException(
            Exception ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
