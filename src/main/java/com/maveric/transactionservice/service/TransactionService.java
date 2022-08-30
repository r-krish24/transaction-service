package com.maveric.transactionservice.service;

import com.maveric.transactionservice.dto.TransactionDto;
import java.util.List;

public interface TransactionService {
    public List<TransactionDto> getTransactions(Integer page, Integer pageSize);
    public TransactionDto createTransaction(TransactionDto transaction);
    public TransactionDto getTransactionById(String transactionId);
    public String deleteTransaction(String transactionId);
}
