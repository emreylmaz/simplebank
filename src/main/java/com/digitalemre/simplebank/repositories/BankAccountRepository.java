package com.digitalemre.simplebank.repositories;

import com.digitalemre.simplebank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {


    BankAccount findByAccountNumber(String accountNumber);
}
