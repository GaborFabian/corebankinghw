package com.example.corebanking.command;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.corebanking.model.Account;
import com.example.corebanking.model.ActiveSession;
import com.example.corebanking.repository.AccountRepository;

@ShellCommandGroup("CoreBanking Commands")
@ShellComponent
public class SelectAccountCommand {

    private static final String ACCOUNT_SELECTED_SUCCESSFULLY = "Account selected successfully: ";
    private static final String ACCOUNT_NOT_EXISTS = "Account does not exist: ";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ActiveSession activeSession;

    @ShellMethod("Select an account.")
    public void select(int accountId) {
        setAccountInSession(accountId);
    }

    private void setAccountInSession(int accountId) {
        Optional<Account> account = accountRepository.findById((long) accountId);
        if (account.isPresent()) {
            activeSession.setAccount(account.get());
            System.out.println(ACCOUNT_SELECTED_SUCCESSFULLY + account.get().getName());
        } else {
            System.out.println(ACCOUNT_NOT_EXISTS + accountId);
        }
    }
}
