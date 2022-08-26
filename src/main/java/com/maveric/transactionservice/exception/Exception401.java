package com.maveric.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Exception401 extends RuntimeException{
     public Exception401(String message)
    {
        super(message);
    }
}
