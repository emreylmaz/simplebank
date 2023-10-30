package com.digitalemre.simplebank.entities;

import com.digitalemre.simplebank.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorColumn(name = "transaction_type", discriminatorType = jakarta.persistence.DiscriminatorType.STRING)
public  class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column(name ="transaction_type" , insertable = false, updatable = false)
    private TransactionType transactionType;

    private String date;

    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @PrePersist
    public void prePersist() {
        this.date = new java.util.Date().toString();
    }

}
