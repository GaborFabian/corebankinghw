package com.example.corebanking.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.corebanking.exception.InvalidAmountException;
import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;
import com.example.corebanking.validator.AmountValidator;

public class DepositServiceTest {

    @Mock
    private AmountValidator amountValidator;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DepositService depositService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void depositShouldUpdateAccount() {
        //GIVEN
        Account account = new Account();
        account.setBalance(200);
        int amount = 100;
        Account expectedAccount = new Account();
        expectedAccount.setBalance(300);
        given(amountValidator.isValidAmount(amount)).willReturn(Boolean.TRUE);
        //WHEN
        depositService.deposit(account, amount);
        //THEN
        verify(accountRepository).save(expectedAccount);
    }

    @Test(expected = InvalidAmountException.class)
    public void depositShouldThrowInvalidAmountExceptionIfAmountIsInvalid() {
        //GIVEN
        Account account = new Account();
        int amount = -100;
        given(amountValidator.isValidAmount(amount)).willThrow(InvalidAmountException.class);
        //WHEN
        depositService.deposit(account, amount);
    }

}