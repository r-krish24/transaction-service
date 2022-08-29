package com.maveric.transactionservice.service;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.exception.Exception404;
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

import static com.maveric.transactionservice.constants.Constants.getCurrentDateTime;
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
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        //Adding CreatedTime
        transactionDto.setCreatedAt(getCurrentDateTime());

        Transaction transaction = mapper.map(transactionDto);
        Transaction transactionResult = repository.save(transaction);
        return  mapper.map(transactionResult);
    }

    @Override
    public TransactionDto getTransactionById(String transactionId) {
        Transaction transactionResult=repository.findById(transactionId).orElseThrow(() -> new Exception404("Transaction not found"));
        return mapper.map(transactionResult);
    }

    @Override
    public String deleteTransaction(String transactionId) throws Exception404 {
            Transaction transactionResult=repository.findById(transactionId).orElseThrow(() -> new Exception404("Transaction not found"));
            repository.deleteById(transactionId);
            return "Transaction deleted successfully.";
    }


}
