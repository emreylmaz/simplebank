package com.digitalemre.simplebank.repositories;

import com.digitalemre.simplebank.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
