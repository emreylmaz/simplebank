package com.digitalemre.simplebank.services;

import com.digitalemre.simplebank.dtos.request.account.CreateBankAccountRequest;
import com.digitalemre.simplebank.dtos.response.account.CreateBankAccountResponse;
import com.digitalemre.simplebank.dtos.response.account.GetBankAccountResponse;
import com.digitalemre.simplebank.entities.BankAccount;
import com.digitalemre.simplebank.entities.Transaction;
import com.digitalemre.simplebank.utilities.results.DataResult;

public interface BankAccountService {

    DataResult<CreateBankAccountResponse> createBankAccount(CreateBankAccountRequest createBankAccountRequest);
    BankAccount getByAccountNumber(String accountNumber);

    DataResult<GetBankAccountResponse> getBankAccountAndTransactions(String accountNumber);

    void saveTransactionForBankAccount(Transaction transaction, BankAccount bankAccount);
}
