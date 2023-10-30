package com.digitalemre.simplebank.entities;

import com.digitalemre.simplebank.enums.TransactionType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue("PHONE_BILL_PAYMENT")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
public class PhoneBillPaymentTransaction extends Transaction{
    private String vendor;
    private String phoneNumber;


    public PhoneBillPaymentTransaction() {
        setTransactionType(TransactionType.PHONE_BILL_PAYMENT);
    }
}
