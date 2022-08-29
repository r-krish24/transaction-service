package com.maveric.transactionservice.exception;

import com.maveric.transactionservice.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class ExceptionControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorDto error = new ErrorDto();
        error.setCode("400");
        error.setMessage("The server could not understand the request due to invalid syntax.");
        return new ResponseEntity(error, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorDto error = new ErrorDto();
        error.setCode("400");
        error.setMessage("The server could not understand the request due to invalid syntax, Kindly enter valid inputs.");
        return new ResponseEntity(error, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorDto error = new ErrorDto();
        error.setCode("405");
        error.setMessage("Method Not Allowed. Kindly check the Request URL and Request Type.");
        return new ResponseEntity(error, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorDto error = new ErrorDto();
        error.setCode("404");
        error.setMessage("The server can not find the requested resource.");
        return new ResponseEntity(error, headers, status);
    }

    @ExceptionHandler(Exception400.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDto handleBadRequest()
    {
        ErrorDto error = new ErrorDto();
        error.setCode("400");
        error.setMessage("The server could not understand the request due to invalid syntax.");
        return error;
    }

    @ExceptionHandler(Exception401.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ErrorDto handleUnAuthorized()
    {
        ErrorDto error = new ErrorDto();
        error.setCode("401");
        error.setMessage("Unauthorized. The client must authenticate itself to get the requested response");
        return error;
    }

    @ExceptionHandler(Exception403.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final ErrorDto handleForbidden() {
        ErrorDto error = new ErrorDto();
        error.setCode("403");
        error.setMessage("The client does not have access rights to the content; that is, it is unauthorized, so the server is refusing to give the requested resource.");
        return error;
    }

    @ExceptionHandler(Exception404.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleNotFound() {
        ErrorDto error = new ErrorDto();
        error.setCode("404");
        error.setMessage("The server can not find the requested resource");
        return error;
    }

    @ExceptionHandler(Exception500.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorDto handleInternalServerError() {
        ErrorDto error = new ErrorDto();
        error.setCode("500");
        error.setMessage("The server has encountered a situation it doesn't know how to handle.");
        return error;
    }

    @ExceptionHandler(Exception503.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public final ErrorDto handleServiceUnavailable() {
        ErrorDto error = new ErrorDto();
        error.setCode("503");
        error.setMessage("The server is not ready to handle the request.");
        return error;
    }

    @ExceptionHandler(Exception.class)
    public final ErrorDto handleException() {
        ErrorDto error = new ErrorDto();
        error.setCode("default");
        error.setMessage("Unexpected error");
        return error;
    }


}
