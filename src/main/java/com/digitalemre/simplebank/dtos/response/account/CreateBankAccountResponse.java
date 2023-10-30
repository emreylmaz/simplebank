package com.digitalemre.simplebank.dtos.response.account;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBankAccountResponse {
    private Long id;
    private String owner;
    private String accountNumber;
}
