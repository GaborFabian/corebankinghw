package com.example.corebanking.service;

import com.example.corebanking.exception.InvalidAmountException;
import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositService {

    @Autowired
    private AccountRepository accountRepository;

    public void deposit(Account account, int amount) throws InvalidAmountException {
        if (isValidAmount(amount)) {
            makeDeposit(account, amount);
        }
    }

    private boolean isValidAmount(int amount) throws InvalidAmountException {
        if(amount <= 0) {
            throw new InvalidAmountException();
        }
        return true;
    }

    private void makeDeposit(Account account, int amount) {
        int newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        System.out.println("Successful deposit. New balance: " + account.getBalance());
    }

}
