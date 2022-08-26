package com.maveric.transactionservice.dto;

import com.maveric.transactionservice.constants.Type;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionDto {

    private String _id;
    private String accountId;
    private Type type;
    private Number amount;
    private LocalDateTime createdAt;
}
