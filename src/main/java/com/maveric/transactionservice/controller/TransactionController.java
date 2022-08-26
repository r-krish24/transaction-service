package com.maveric.transactionservice.controller;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.exception.Exception400;
import com.maveric.transactionservice.exception.Exception404;
import com.maveric.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable String accountId,@RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam(defaultValue = "10") Integer pageSize) throws Exception400,Exception404 {
        List<TransactionDto> transactionDtoResponse = transactionService.getTransactions(page,pageSize);
        return new ResponseEntity<>(transactionDtoResponse, HttpStatus.OK);
    }

    @PostMapping("accounts/{accountId}/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@PathVariable String accountId, @RequestBody TransactionDto transactionDto) throws Exception400,Exception404 {
        TransactionDto transactionDtoResponse = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(transactionDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("accounts/{accountId}/transactions/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionDetails(@PathVariable String accountId,@PathVariable String transactionId) throws Exception400,Exception404{
        TransactionDto transactionDtoResponse = transactionService.getTransactionById(transactionId);
        return new ResponseEntity<>(transactionDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("accounts/{accountId}/transactions/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable String accountId,@PathVariable String transactionId) throws Exception400,Exception404 {
        String result = transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
