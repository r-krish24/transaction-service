package com.maveric.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class Exception403 extends RuntimeException{
    public Exception403(String message) {
        super(message);
    }
}
