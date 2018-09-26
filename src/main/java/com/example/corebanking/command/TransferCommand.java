package com.example.corebanking.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.corebanking.model.ActiveSession;
import com.example.corebanking.service.TransferService;

@ShellCommandGroup("CoreBanking Commands")
@ShellComponent
public class TransferCommand {

    private static final String NO_ACCOUNT_SELECTED = "no account selected. Select an account with the 'select' command.";

    @Autowired
    private ActiveSession activeSession;

    @Autowired
    private TransferService transferService;

    @ShellMethod("Transfer certain amount of money from the selected bank account to an another account")
    public void transfer(int accountID, int amount) {
        transferService.transfer(activeSession.getAccount(), accountID, amount);
    }

    public Availability transferAvailability() {
        return activeSession.getAccount() != null
                ? Availability.available()
                : Availability.unavailable(NO_ACCOUNT_SELECTED);
    }
}

