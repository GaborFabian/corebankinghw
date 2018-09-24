package com.example.corebanking.service;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.corebanking.exception.InvalidAmountException;
import com.example.corebanking.exception.NotEnoughBalanceException;
import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;


public class WithdrawServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private WithdrawService withdrawService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void withdrawShouldUpdateAccount() throws Throwable {
        //GIVEN
        Account account = new Account();
        account.setBalance(200);
        int amount = 100;
        Account expectedAccount = new Account();
        expectedAccount.setBalance(100);
        //WHEN
        withdrawService.withdraw(account, amount);
        //THEN
        verify(accountRepository).save(expectedAccount);
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void withdrawShouldThrowNotEnoughBalanceException() throws Throwable {
        //GIVEN
        Account account = new Account();
        account.setBalance(100);
        int amount = 200;
        //WHEN
        withdrawService.withdraw(account, amount);
    }

    @Test(expected = InvalidAmountException.class)
    public void withdrawShouldThrowInvalidAmountExceptionIfAmountIsZero() throws Throwable {
        //GIVEN
        Account account = new Account();
        int amount = 0;
        //WHEN
        withdrawService.withdraw(account, amount);
    }

    @Test(expected = InvalidAmountException.class)
    public void withdrawShouldThrowInvalidAmountExceptionIfAmountIsNegative() throws Throwable {
        //GIVEN
        Account account = new Account();
        int amount = -100;
        //WHEN
        withdrawService.withdraw(account, amount);
    }

}