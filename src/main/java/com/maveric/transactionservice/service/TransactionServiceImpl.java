package com.maveric.transactionservice.service;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.mapper.TransactionMapper;
import com.maveric.transactionservice.mapper.TransactionMapperImpl;
import com.maveric.transactionservice.model.Transaction;
import com.maveric.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.maveric.transactionservice.constants.Constants.getCurrentDateTime;
import static com.maveric.transactionservice.utility.ModelDtoTransformer.toDto;
import static com.maveric.transactionservice.utility.ModelDtoTransformer.toEntity;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = mapper.map(transactionDto);
        Transaction transactionResult = repository.save(transaction);
        TransactionDto transactionDtoResult = mapper.map(transactionResult);
        //Adding CreatedTime
        transactionDtoResult.setCreatedAt(getCurrentDateTime());
        return transactionDtoResult;
    }
}
