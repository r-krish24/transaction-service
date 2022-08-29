package com.maveric.transactionservice.dto;

import com.maveric.transactionservice.constants.Type;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TransactionDto {

    private String _id;
    @NotNull
    private String accountId;
    @NotNull
    private Type type;
    @NotNull
    private Number amount;
    private LocalDateTime createdAt;
}
