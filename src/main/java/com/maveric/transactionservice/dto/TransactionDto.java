package com.maveric.transactionservice.dto;

import com.maveric.transactionservice.constants.Type;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionDto {

    private String _id;
    private String accountId;
    private Type type;
    private Number amount;
    private LocalDateTime createdAt;
}
