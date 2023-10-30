package com.digitalemre.simplebank.services;

import com.digitalemre.simplebank.dtos.request.transaction.CreateCreditRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreatePhoneBillPaymentRequest;
import com.digitalemre.simplebank.dtos.request.transaction.CreateWithdrawalRequest;
import com.digitalemre.simplebank.dtos.response.transaction.CreateCreditResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreatePhoneBillPaymentResponse;
import com.digitalemre.simplebank.dtos.response.transaction.CreateWithdrawalResponse;
import com.digitalemre.simplebank.utilities.results.DataResult;

public interface TransactionService {
    DataResult<CreateCreditResponse> creditAccount(String accountNumber, CreateCreditRequest createCreditRequest);
    DataResult<CreateWithdrawalResponse> withdrawAccount(String accountNumber, CreateWithdrawalRequest createWithdrawalRequest);

    DataResult<CreatePhoneBillPaymentResponse> phoneBillPayment(String accountNumber, CreatePhoneBillPaymentRequest createPhoneBillPaymentRequest);
}
