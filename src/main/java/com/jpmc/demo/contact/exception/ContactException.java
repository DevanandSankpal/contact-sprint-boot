package com.jpmc.demo.contact.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ContactException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    @Override
    public  String getMessage(){
        return message;
    }

}
