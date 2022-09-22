package com.maveric.transactionservice.dto;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class BalanceDto {
    private String  _id;
    private Number amount;
}
