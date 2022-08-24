package com.maveric.transactionservice.service;

import com.maveric.transactionservice.dto.TransactionDto;

public interface TransactionService {
    public TransactionDto createTransaction(TransactionDto transaction);
}
