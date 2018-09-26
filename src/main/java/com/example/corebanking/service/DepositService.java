package com.example.corebanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.corebanking.exception.InvalidAmountException;
import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;
import com.example.corebanking.validator.AmountValidator;

@Component
public class DepositService {

    @Autowired
    private AmountValidator amountValidator;

    @Autowired
    private AccountRepository accountRepository;

    public void deposit(Account account, int amount) throws InvalidAmountException {
        if (amountValidator.isValidAmount(amount)) {
            makeDeposit(account, amount);
        }
    }

    private void makeDeposit(Account account, int amount) {
        int newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        System.out.println("Successful deposit. New balance: " + account.getBalance());
    }

}
