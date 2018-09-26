package com.example.corebanking.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.example.corebanking.exception.NotEnoughBalanceException;
import com.example.corebanking.model.Account;

public class BalanceValidatorTest {

    private BalanceValidator balanceValidator;

    @Before
    public void setUp() {
        balanceValidator = new BalanceValidator();
    }

    @Test
    public void isEnoughBalanceShouldReturnTrue() {
        //GIVEN
        Account account = new Account();
        account.setBalance(1000);
        int amount = 100;
        //WHEN
        boolean actual = balanceValidator.isEnoughBalance(account, amount);
        //THEN
        assertTrue(actual);
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void isEnoughBalanceShouldThrowNotEnoughBalanceException() {
        //GIVEN
        Account account = new Account();
        account.setBalance(100);
        int amount = 1000;
        //WHEN
        balanceValidator.isEnoughBalance(account, amount);
    }

}