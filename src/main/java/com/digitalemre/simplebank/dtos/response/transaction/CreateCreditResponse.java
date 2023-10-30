package com.digitalemre.simplebank.dtos.response.transaction;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCreditResponse {
    private Long id;
    private String approvalCode;
}
