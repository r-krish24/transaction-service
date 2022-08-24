package com.maveric.transactionservice.mapper;

import com.maveric.transactionservice.dto.TransactionDto;
import com.maveric.transactionservice.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="Transaction")
public interface TransactionMapper {

    Transaction map(TransactionDto transactionDto);

    TransactionDto map(Transaction transaction);

    List<Transaction> map (List<TransactionDto> transactions);
}
