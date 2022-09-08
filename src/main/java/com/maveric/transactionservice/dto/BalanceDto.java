package com.maveric.transactionservice.dto;

import com.maveric.transactionservice.constants.CurrencyType;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class BalanceDto {
    private String  _id;
    private String accountId;
    private Number amount;
    private CurrencyType currency;
}
