package com.digitalemre.simplebank.dtos.request.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePhoneBillPaymentRequest {
    private String vendor;
    private String phoneNumber;
    private double amount;
}
