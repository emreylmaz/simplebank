package com.digitalemre.simplebank.services.impl;

import com.digitalemre.simplebank.dtos.request.transaction.CreateCreditRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreatePhoneBillPaymentRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreateWithdrawalRequest;
import com.digitalemre.simplebank.dtos.response.transaction.CreateCreditResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreatePhoneBillPaymentResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreateWithdrawalResponse;
import com.digitalemre.simplebank.entities.*;
import com.digitalemre.simplebank.repositories.TransactionRepository;
import com.digitalemre.simplebank.services.BankAccountService;
import com.digitalemre.simplebank.services.TransactionService;
import com.digitalemre.simplebank.utilities.exceptions.BusinessException;
import com.digitalemre.simplebank.utilities.results.DataResult;
import com.digitalemre.simplebank.utilities.results.SuccessDataResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;

    TransactionServiceImpl(TransactionRepository transactionRepository, BankAccountService bankAccountService) {
        this.transactionRepository = transactionRepository;
        this.bankAccountService = bankAccountService;
    }


    @Override
    public DataResult<CreateCreditResponse> creditAccount(String accountNumber, CreateCreditRequest createCreditRequest) {
        BankAccount bankAccount = bankAccountService.getByAccountNumber(accountNumber);
        isBankAccountNull(bankAccount);


        bankAccount.setBalance(bankAccount.getBalance() + createCreditRequest.getAmount());

        String approvalCode = generateApprovalCode();

        Transaction transaction = DepositTransaction.builder()
                .amount(createCreditRequest.getAmount())
                .approvalCode(approvalCode)
                .bankAccount(bankAccount)
                .build();

        saveTransaction(transaction);


        CreateCreditResponse createCreditResponse = CreateCreditResponse.builder()
                .id(transaction.getId())
                .approvalCode(approvalCode)
                .build();

        return new SuccessDataResult<>(createCreditResponse, "Credit transaction created successfully");


    }

    @Override
    public DataResult<CreateWithdrawalResponse> withdrawAccount(String accountNumber, CreateWithdrawalRequest createWithdrawalRequest) {
        BankAccount bankAccount = bankAccountService.getByAccountNumber(accountNumber);
        isBankAccountNull(bankAccount);

        isBankAccountBalanceSufficient(bankAccount, createWithdrawalRequest.getAmount());

        bankAccount.setBalance(bankAccount.getBalance() - createWithdrawalRequest.getAmount());

        String approvalCode = generateApprovalCode();

        Transaction transaction = WithdrawalTransaction.builder()
                .amount(createWithdrawalRequest.getAmount())
                .approvalCode(approvalCode)
                .bankAccount(bankAccount)
                .build();

        saveTransaction(transaction);

        bankAccountService.saveTransactionForBankAccount(transaction, bankAccount);

        CreateWithdrawalResponse createWithdrawalResponse = CreateWithdrawalResponse.builder()
                .id(transaction.getId())
                .approvalCode(approvalCode)
                .build();

        return new SuccessDataResult<>(createWithdrawalResponse, "Withdrawal transaction created successfully");


    }

    @Override
    public DataResult<CreatePhoneBillPaymentResponse> phoneBillPayment(String accountNumber, CreatePhoneBillPaymentRequest createPhoneBillPaymentRequest) {
        BankAccount bankAccount = bankAccountService.getByAccountNumber(accountNumber);
        isBankAccountNull(bankAccount);


        isBankAccountBalanceSufficient(bankAccount, createPhoneBillPaymentRequest.getAmount());


        bankAccount.setBalance(bankAccount.getBalance() - createPhoneBillPaymentRequest.getAmount());

        String approvalCode = generateApprovalCode();

        Transaction transaction = PhoneBillPaymentTransaction.builder()
                .amount(createPhoneBillPaymentRequest.getAmount())
                .phoneNumber(createPhoneBillPaymentRequest.getPhoneNumber())
                .vendor(createPhoneBillPaymentRequest.getVendor())
                .approvalCode(approvalCode)
                .bankAccount(bankAccount)
                .build();

        saveTransaction(transaction);

        bankAccountService.saveTransactionForBankAccount(transaction, bankAccount);

        CreatePhoneBillPaymentResponse createPhoneBillPaymentResponse = CreatePhoneBillPaymentResponse.builder()
                .id(transaction.getId())
                .approvalCode(approvalCode)
                .build();

        return new SuccessDataResult<>(createPhoneBillPaymentResponse, "Phone bill payment transaction created successfully");


    }

    private String generateApprovalCode() {
        return UUID.randomUUID().toString();
    }

    private void isBankAccountBalanceSufficient(BankAccount bankAccount, double amount) {
        if (bankAccount.getBalance() < amount) {
            throw new BusinessException("Insufficient balance");
        }
    }

    private void isBankAccountNull(BankAccount bankAccount) {
        if (bankAccount == null) {
            throw new BusinessException("Bank account not found");
        }
    }

    private void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
