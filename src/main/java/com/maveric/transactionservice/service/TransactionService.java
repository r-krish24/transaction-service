package com.maveric.transactionservice.service;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.exception.TransactionNotFoundException;

import java.util.List;

public interface TransactionService {
    public List<TransactionDto> getTransactions(Integer page, Integer pageSize);

    public List<TransactionDto> getTransactionsByAccountId(String accountId);
    public TransactionDto createTransaction(TransactionDto transaction);
    public TransactionDto getTransactionById(String transactionId) throws TransactionNotFoundException;
    public String deleteTransaction(String transactionId);
}
