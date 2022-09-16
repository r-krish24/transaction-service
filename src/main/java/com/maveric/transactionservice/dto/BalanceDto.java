package com.maveric.transactionservice.dto;

import com.maveric.transactionservice.constants.CurrencyType;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BalanceDto {
    private String  _id;
    private String accountId;
    private Number amount;
    private CurrencyType currency;
}
