package com.digitalemre.simplebank.services.impl;

import com.digitalemre.simplebank.dtos.request.account.CreateBankAccountRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreateCreditRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreateWithdrawalRequest;
import com.digitalemre.simplebank.dtos.response.account.CreateBankAccountResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreateCreditResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreateWithdrawalResponse;
import com.digitalemre.simplebank.entities.BankAccount;
import com.digitalemre.simplebank.repositories.BankAccountRepository;

import com.digitalemre.simplebank.services.BankAccountService;
import com.digitalemre.simplebank.services.TransactionService;
import com.digitalemre.simplebank.utilities.results.DataResult;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceImplTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionService transactionService;



    @Test
    public void createBankAccountTest() {
        CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest("John Doe");

        DataResult<CreateBankAccountResponse> result = bankAccountService.createBankAccount(createBankAccountRequest);

        assertEquals(result.isSuccess(), true);
        assertEquals(result.getMessage(), "Bank account created successfully");

        BankAccount bankAccounts = bankAccountRepository.findByAccountNumber(result.getData().getAccountNumber());
        assertEquals(bankAccounts.getAccountNumber(), result.getData().getAccountNumber());
        assertEquals(bankAccounts.getOwner(), "John Doe");
    }


    @Test
    public void createBankAccountAndCreateCreditTest() {
        CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest("John Doe Credit");
        DataResult<CreateBankAccountResponse> resultBankAccount = bankAccountService.createBankAccount(createBankAccountRequest);

        String accountNumber =   resultBankAccount.getData().getAccountNumber();



        CreateCreditRequest createCreditRequest = new CreateCreditRequest(1000.0);

        DataResult<CreateCreditResponse> result = transactionService.creditAccount(accountNumber, createCreditRequest);

        assertEquals(result.isSuccess(), true);
        assertEquals(result.getMessage(), "Credit transaction created successfully");


    }

  /*  @Test
    public void createBankAccountAndCreateCreditAndCreateWithdrawalTest() {
        CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest("John Doe Withdrawal");
        DataResult<CreateBankAccountResponse> resultBankAccount = bankAccountService.createBankAccount(createBankAccountRequest);
        String accountNumber =   resultBankAccount.getData().getAccountNumber();
        CreateCreditRequest createCreditRequest = new CreateCreditRequest(1000.0);
        DataResult<CreateCreditResponse> resultCredit = transactionService.creditAccount(accountNumber, createCreditRequest);
        CreateWithdrawalRequest createWithdrawalRequest = new CreateWithdrawalRequest(5.0);
        DataResult<CreateWithdrawalResponse> resultWithdrawal = transactionService.withdrawAccount(accountNumber, createWithdrawalRequest);
        assertEquals(resultWithdrawal.isSuccess(), true);
    } */









}
