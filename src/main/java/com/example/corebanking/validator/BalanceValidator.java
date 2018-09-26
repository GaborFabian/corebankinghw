package com.example.corebanking.validator;

import org.springframework.stereotype.Component;

import com.example.corebanking.exception.NotEnoughBalanceException;
import com.example.corebanking.model.Account;

@Component
public class BalanceValidator {

    public boolean isEnoughBalance(Account account, int amount) {
        if(account.getBalance() < amount) {
            throw new NotEnoughBalanceException();
        }
        return true;
    }

}
