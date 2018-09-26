package com.example.corebanking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.corebanking.exception.AccountDoesNotExistException;
import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;

@Component
public class TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private DepositService depositService;

    public void transfer(Account accountFrom, int accountToID, int amount) {
        Optional<Account> accountTo = accountRepository.findById((long) accountToID);
        if (accountTo.isPresent()) {
            withdrawService.withdraw(accountFrom, amount);
            depositService.deposit(accountTo.get(), amount);
        } else {
            throw new AccountDoesNotExistException(accountToID);
        }
    }
}
