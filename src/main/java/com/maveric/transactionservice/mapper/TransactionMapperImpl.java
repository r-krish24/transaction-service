package com.maveric.transactionservice.mapper;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapperImpl implements TransactionMapper{
    @Override
    public Transaction map(TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.get_id(),
                transactionDto.getAccountId(),
                transactionDto.getType(),
                transactionDto.getAmount(),
                transactionDto.getCreatedAt()
        );
    }

    @Override
    public TransactionDto map(Transaction transaction) {
        return new TransactionDto(
                transaction.get_id(),
                transaction.getAccountId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getCreatedAt()
        );
    }

    @Override
    public List<Transaction> mapToModel(List<TransactionDto> transactions) {
        List<Transaction> list = new ArrayList<>(transactions.size());
        for(TransactionDto transactionDto:transactions)
        {
            list.add(map(transactionDto));
        }
        return list;
    }

    @Override
    public List<TransactionDto> mapToDto(List<Transaction> transactions) {
        List<TransactionDto> list = new ArrayList<>(transactions.size());
        for(Transaction transaction:transactions)
        {
            list.add(map(transaction));
        }
        return list;
    }


}
