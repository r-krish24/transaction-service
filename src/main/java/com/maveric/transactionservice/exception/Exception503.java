package com.maveric.transactionservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class Exception503 extends RuntimeException{
    public Exception503(String message) {
        super(message);
    }
}
