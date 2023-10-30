package com.digitalemre.simplebank.entities;

import com.digitalemre.simplebank.enums.TransactionType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@DiscriminatorValue("WITHDRAWAL")
@SuperBuilder(toBuilder = true)
public class WithdrawalTransaction  extends Transaction{


    public WithdrawalTransaction() {
        setTransactionType(TransactionType.WITHDRAWAL);
    }
}
