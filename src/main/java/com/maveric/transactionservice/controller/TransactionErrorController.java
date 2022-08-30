package com.maveric.transactionservice.controller;

import com.maveric.transactionservice.dto.ErrorDto;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TransactionErrorController implements ErrorController {

    @GetMapping("/error")
    public ResponseEntity<ErrorDto> errorHandler(HttpServletRequest req) {
        ErrorDto error = new ErrorDto();
        error.setCode("404");
        error.setMessage("The server can not find the requested resource.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}