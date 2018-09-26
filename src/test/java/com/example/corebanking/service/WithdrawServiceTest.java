package com.example.corebanking.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;
import com.example.corebanking.validator.AmountValidator;
import com.example.corebanking.validator.BalanceValidator;

public class WithdrawServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AmountValidator amountValidator;

    @Mock
    private BalanceValidator balanceValidator;

    @InjectMocks
    private WithdrawService withdrawService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void withdrawShouldUpdateAccount() {
        //GIVEN
        Account account = new Account();
        account.setBalance(200);
        int amount = 100;
        Account expectedAccount = new Account();
        expectedAccount.setBalance(100);
        given(amountValidator.isValidAmount(amount)).willReturn(Boolean.TRUE);
        given(balanceValidator.isEnoughBalance(account, amount)).willReturn(Boolean.TRUE);
        //WHEN
        withdrawService.withdraw(account, amount);
        //THEN
        verify(accountRepository).save(expectedAccount);
    }

    @Test
    public void withdrawShouldNotUpdateAccountIfAmountIsInvalid() {
        //GIVEN
        Account account = new Account();
        int amount = -100;
        given(amountValidator.isValidAmount(amount)).willReturn(Boolean.FALSE);
        //WHEN
        withdrawService.withdraw(account, amount);
        //THEN
        verify(accountRepository, never()).save(any());
    }

    @Test
    public void withdrawShouldNotUpdateAccountIfNotEnoughBalance() {
        //GIVEN
        Account account = new Account();
        int amount = 100;
        given(amountValidator.isValidAmount(amount)).willReturn(Boolean.TRUE);
        given(balanceValidator.isEnoughBalance(account, amount)).willReturn(Boolean.FALSE);
        //WHEN
        withdrawService.withdraw(account, amount);
        //THEN
        verify(accountRepository, never()).save(any());
    }

}