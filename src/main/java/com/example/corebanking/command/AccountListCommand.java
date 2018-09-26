package com.example.corebanking.command;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;

@ShellCommandGroup("CoreBanking Commands")
@ShellComponent
public class AccountListCommand {

    @Autowired
    private AccountRepository accountRepository;

    @ShellMethod("List all bank accounts.")
    public void list() {
        List<Account> all = accountRepository.findAll();
        all.forEach(a -> printOutAccount(a));
    }

    private void printOutAccount(Account account) {
        System.out.printf("%-5d%-20s%d\n", account.getId(), account.getName(), account.getBalance());
    }

}
