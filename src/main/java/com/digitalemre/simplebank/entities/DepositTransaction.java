package com.digitalemre.simplebank.entities;


import com.digitalemre.simplebank.enums.TransactionType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("DEPOSIT")
@Data
@SuperBuilder(toBuilder = true)


public class DepositTransaction extends Transaction {


    public DepositTransaction() {
        setTransactionType(TransactionType.DEPOSIT);
    }
}
