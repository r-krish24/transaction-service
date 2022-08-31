package com.maveric.transactionservice.service;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.exception.TransactionNotFoundException;
import com.maveric.transactionservice.mapper.TransactionMapper;
import com.maveric.transactionservice.model.Transaction;
import com.maveric.transactionservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.maveric.transactionservice.constants.Constants.*;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public List<TransactionDto> getTransactions(Integer page, Integer pageSize) {
        Pageable paging = PageRequest.of(page, pageSize);
        Page<Transaction> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            List<Transaction> transaction = pageResult.getContent();
            return mapper.mapToDto(transaction);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(String accountId) {
        List<Transaction> transactions = repository.findByAccountId(accountId);
            return mapper.mapToDto(transactions);
    }


    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        //Adding CreatedTime
        transactionDto.setCreatedAt(getCurrentDateTime());

        Transaction transaction = mapper.map(transactionDto);
        Transaction transactionResult = repository.save(transaction);
        return  mapper.map(transactionResult);
    }



    @Override
    public TransactionDto getTransactionById(String transactionId) {
        Transaction transactionResult=repository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException(TRANSACTION_NOT_FOUND_MESSAGE+transactionId));
        return mapper.map(transactionResult);
    }

    @Override
    public String deleteTransaction(String transactionId) {
            if(!repository.findById(transactionId).isPresent())
            {
                throw new TransactionNotFoundException(TRANSACTION_NOT_FOUND_MESSAGE+transactionId);
            }
            repository.deleteById(transactionId);
            return TRANSACTION_DELETED_SUCCESS;
    }


}
