package com.example.corebanking.service;

import com.example.corebanking.exception.InvalidAmountException;
import com.example.corebanking.exception.NotEnoughBalanceException;
import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawService {

    @Autowired
    private AccountRepository accountRepository;

    public void withdraw(Account account, int amount) throws NotEnoughBalanceException, InvalidAmountException {
        if (isValidAmount(amount) && isEnoughBalance(account, amount)) {
            makeWithdraw(account, amount);
        }
    }

    private boolean isEnoughBalance(Account account, int amount) throws NotEnoughBalanceException {
        if(account.getBalance() < amount) {
            throw new NotEnoughBalanceException();
        }
        return true;
    }

    private boolean isValidAmount(int amount) throws InvalidAmountException {
        if(amount <= 0) {
            throw new InvalidAmountException();
        }
        return true;
    }

    private void makeWithdraw(Account account, int amount) {
        int newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        System.out.println("Successful withdrawal. New balance: " + account.getBalance());
    }

}
