package com.example.corebanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;
import com.example.corebanking.validator.AmountValidator;
import com.example.corebanking.validator.BalanceValidator;

@Component
public class WithdrawService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AmountValidator amountValidator;

    @Autowired
    private BalanceValidator balanceValidator;

    public void withdraw(Account account, int amount) {
        if (amountValidator.isValidAmount(amount) && balanceValidator.isEnoughBalance(account, amount)) {
            makeWithdraw(account, amount);
        }
    }

    private void makeWithdraw(Account account, int amount) {
        int newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        accountRepository.save(account);
        System.out.println("Successful withdrawal. New balance: " + account.getBalance());
    }

}
