package com.maveric.transactionservice.exception;

import com.maveric.transactionservice.dto.ErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExceptionControllerAdvisorTest {

    private ExceptionControllerAdvisor controllerAdvisor = new ExceptionControllerAdvisor();

    @Test
    void handleTransactionNotFoundException() {
        TransactionNotFoundException exception = new TransactionNotFoundException("Transaction Not found");
        ErrorDto error = controllerAdvisor.handleTransactionNotFoundException(exception);
        assertEquals("404",error.getCode());
    }



    @Test
    void handlePathParamsVsInputParamsMismatchException() {
        PathParamsVsInputParamsMismatchException exception = new PathParamsVsInputParamsMismatchException("Not match");
        ErrorDto error = controllerAdvisor.handlePathParamsVsInputParamsMismatchException(exception);
        assertEquals("400",error.getCode());
    }

    @Test
    void handleInsufficientBalanceException() {
        InsufficientBalanceException exception = new InsufficientBalanceException("Insufficient balance");
        ErrorDto error = controllerAdvisor.handleInsufficientBalanceException(exception);
        assertEquals("400",error.getCode());
    }
}