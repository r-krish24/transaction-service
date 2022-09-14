package com.maveric.transactionservice.exception;

import com.maveric.transactionservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static com.maveric.transactionservice.constants.Constants.*;

@RestControllerAdvice
public class ExceptionControllerAdvisor {

    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleTransactionNotFoundException(TransactionNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(TRANSACTION_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return errorDto;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDto handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(METHOD_NOT_ALLOWED_CODE);
        errorDto.setMessage(METHOD_NOT_ALLOWED_MESSAGE);
        return errorDto;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleHttpMessageNotReadableException (
            HttpMessageNotReadableException ex) throws NullPointerException {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        String message = ex.getMessage()==null?"":ex.getMessage(); //NOSONAR
        try {
                if (message.contains("com.maveric.transactionservice.constants.Type")) //NOSONAR
                    errorDto.setMessage(INVALID_INPUT_TYPE);
                else
                    errorDto.setMessage(INVALID_INPUT_MESSAGE);
        }
        catch(NullPointerException e)
        {
            System.err.println("Error with handling HttpMessageNotReadableException");
            throw new NullPointerException();
        }
        catch(Exception e)
        {
            System.err.println("Error with handling HttpMessageNotReadableException");
        }
        return errorDto;
    }

    @ExceptionHandler(PathParamsVsInputParamsMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDto handlePathParamsVsInputParamsMismatchException(PathParamsVsInputParamsMismatchException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDto handleInsufficientBalanceException(InsufficientBalanceException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

}
