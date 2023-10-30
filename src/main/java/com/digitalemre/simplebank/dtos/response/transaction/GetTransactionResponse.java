package com.digitalemre.simplebank.dtos.response.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionResponse {
    private String date;
    private double amount;
    private String type;
    private String approvalCode;

}
