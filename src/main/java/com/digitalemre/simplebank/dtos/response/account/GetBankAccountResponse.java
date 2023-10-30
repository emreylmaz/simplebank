package com.digitalemre.simplebank.dtos.response.account;


import com.digitalemre.simplebank.dtos.response.transaction.GetTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBankAccountResponse {
    private String accountNumber;
    private String owner;
    private double balance;
    private String createDate;
    private List<GetTransactionResponse> transactions;
}
