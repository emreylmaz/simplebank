package com.digitalemre.simplebank.dtos.request.transaction;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCreditRequest {
    private double amount;
}
