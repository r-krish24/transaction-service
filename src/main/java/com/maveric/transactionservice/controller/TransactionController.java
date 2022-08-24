package com.maveric.transactionservice.controller;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PutMapping("accounts/{accountId}/transactions")
    public ResponseEntity<TransactionDto> createTransaction(@PathVariable String accountId, @RequestBody TransactionDto transactionDto) {
        TransactionDto transactionDtoResponse = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<TransactionDto>(transactionDtoResponse, HttpStatus.OK);
    }


}
