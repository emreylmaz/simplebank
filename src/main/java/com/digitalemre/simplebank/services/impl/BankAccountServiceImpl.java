package com.digitalemre.simplebank.services.impl;

import com.digitalemre.simplebank.dtos.request.account.CreateBankAccountRequest;
import com.digitalemre.simplebank.dtos.response.account.CreateBankAccountResponse;
import com.digitalemre.simplebank.dtos.response.account.GetBankAccountResponse;
import com.digitalemre.simplebank.dtos.response.transaction.GetTransactionResponse;
import com.digitalemre.simplebank.entities.*;
import com.digitalemre.simplebank.repositories.BankAccountRepository;
import com.digitalemre.simplebank.services.BankAccountService;
import com.digitalemre.simplebank.utilities.exceptions.BusinessException;
import com.digitalemre.simplebank.utilities.results.DataResult;
import com.digitalemre.simplebank.utilities.results.SuccessDataResult;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public DataResult<CreateBankAccountResponse> createBankAccount(CreateBankAccountRequest createBankAccountRequest) {
        BankAccount bankAccount = BankAccount.builder()
                .owner(createBankAccountRequest.getOwner())
                .accountNumber(generateUniqueAccountNumber())
                .build();

        bankAccountRepository.save(bankAccount);

        CreateBankAccountResponse createBankAccountResponse = CreateBankAccountResponse.builder()
                .id(bankAccount.getId())
                .owner(bankAccount.getOwner())
                .accountNumber(bankAccount.getAccountNumber())
                .build();

        return new SuccessDataResult<>(createBankAccountResponse, "Bank account created successfully");
    }




    @Override
    public DataResult<GetBankAccountResponse> getBankAccountAndTransactions(String accountNumber) {
        BankAccount bankAccount = getByAccountNumber(accountNumber);
        isBankAccountNull(bankAccount);
        List<GetTransactionResponse> transactionResponses = bankAccount.getTransactions().stream()
                .map(transaction -> {
                    GetTransactionResponse.GetTransactionResponseBuilder builder = GetTransactionResponse.builder()
                            .date(transaction.getDate().toString())
                            .amount(transaction.getAmount())
                            .approvalCode(transaction.getApprovalCode())
                            .type(transaction.getTransactionType().toString());
                    return builder.build();
                }).collect(Collectors.toList());
        GetBankAccountResponse getBankAccountResponse = GetBankAccountResponse.builder()
                .accountNumber(bankAccount.getAccountNumber())
                .owner(bankAccount.getOwner())
                .balance(bankAccount.getBalance())
                .createDate(bankAccount.getDate().toString())
                .transactions(transactionResponses)
                .build();
        return new SuccessDataResult<>(getBankAccountResponse, "Bank account and transactions listed successfully");


    }

    @Override
    public BankAccount getByAccountNumber(String accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);

        return bankAccount;
    }


    public void saveTransactionForBankAccount(Transaction transaction, BankAccount bankAccount) {


        bankAccount.getTransactions().add(transaction);

        bankAccountRepository.save(bankAccount);
    }



    public String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            String randomNumber = generateRandomNumber();
            accountNumber = randomNumber.substring(0, 3) + "-" + randomNumber.substring(3);
        } while (!isAccountNumberUnique(accountNumber));
        return accountNumber;
    }


    private String generateRandomNumber() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return String.valueOf(randomNumber);
    }

    private boolean isAccountNumberUnique(String accountNumber) {

        BankAccount existingAccount = bankAccountRepository.findByAccountNumber(accountNumber);
        return existingAccount == null;
    }


    private void isBankAccountNull(BankAccount bankAccount) {
        if (bankAccount == null) {
            throw new BusinessException("Bank account not found");
        }
    }
}
