package com.maveric.transactionservice.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Exception404 extends RuntimeException{
    public Exception404(String message) {
        super(message);
    }
}
