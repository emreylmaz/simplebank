package com.digitalemre.simplebank.controllers;

import com.digitalemre.simplebank.dtos.request.account.CreateBankAccountRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreateCreditRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreatePhoneBillPaymentRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreateWithdrawalRequest;
import com.digitalemre.simplebank.dtos.response.account.CreateBankAccountResponse;
import com.digitalemre.simplebank.dtos.response.account.GetBankAccountResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreateCreditResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreatePhoneBillPaymentResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreateWithdrawalResponse;
import com.digitalemre.simplebank.services.BankAccountService;
import com.digitalemre.simplebank.services.TransactionService;
import com.digitalemre.simplebank.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account/v1")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    private final TransactionService transactionService;

    public BankAccountController(BankAccountService bankAccountService, TransactionService transactionService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public DataResult<CreateBankAccountResponse> createBankAccount(@RequestBody CreateBankAccountRequest createBankAccountRequest){
        return bankAccountService.createBankAccount(createBankAccountRequest);
    }

    @PostMapping("/credit/{accountNumber}")
    public DataResult<CreateCreditResponse> creditAccount(
            @PathVariable String accountNumber,
            @RequestBody CreateCreditRequest createCreditRequest){

        return transactionService.creditAccount(accountNumber, createCreditRequest);
        }

    @PostMapping("/withdraw/{accountNumber}")
    public DataResult<CreateWithdrawalResponse> withdrawAccount(
            @PathVariable String accountNumber,
            @RequestBody CreateWithdrawalRequest createWithdrawalRequest){

        return transactionService.withdrawAccount(accountNumber, createWithdrawalRequest);
    }

    @PostMapping("/phone-bill-payment/{accountNumber}")
    public DataResult<CreatePhoneBillPaymentResponse> phoneBillPayment(
            @PathVariable String accountNumber,
            @RequestBody CreatePhoneBillPaymentRequest createPhoneBillPaymentRequest){

        return transactionService.phoneBillPayment(accountNumber, createPhoneBillPaymentRequest);
    }

    @GetMapping("/get/{accountNumber}")
    public DataResult<GetBankAccountResponse> getBankAccountAndTransactions(@PathVariable String accountNumber){
        return bankAccountService.getBankAccountAndTransactions(accountNumber);
    }


}
