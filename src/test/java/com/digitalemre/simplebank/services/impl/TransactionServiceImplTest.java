package com.digitalemre.simplebank.services.impl;

import com.digitalemre.simplebank.dtos.request.account.CreateBankAccountRequest;
import com.digitalemre.simplebank.dtos.response.account.CreateBankAccountResponse;
import com.digitalemre.simplebank.entities.BankAccount;
import com.digitalemre.simplebank.repositories.BankAccountRepository;
import com.digitalemre.simplebank.repositories.TransactionRepository;
import com.digitalemre.simplebank.services.BankAccountService;
import com.digitalemre.simplebank.services.TransactionService;
import com.digitalemre.simplebank.utilities.results.DataResult;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class TransactionServiceImplTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void createBankAccountTest() {
        // Test için hazırlanan request
        CreateBankAccountRequest createBankAccountRequest = new CreateBankAccountRequest("John Doe");

        // BankAccountService sınıfından createBankAccount metodunu çağırıyoruz
        DataResult<CreateBankAccountResponse> result = bankAccountService.createBankAccount(createBankAccountRequest);

        // Sonuçları doğruluyoruz
        assertEquals(result.isSuccess(), true);
        assertEquals(result.getMessage(), "Bank account created successfully");

        // Database'den yeni kaydın olup olmadığını kontrol ediyoruz
        BankAccount bankAccounts = bankAccountRepository.findByAccountNumber(result.getData().getAccountNumber());
        assertEquals(bankAccounts.getAccountNumber(), result.getData().getAccountNumber());
        assertEquals(bankAccounts.getOwner(), "John Doe");
    }










}
