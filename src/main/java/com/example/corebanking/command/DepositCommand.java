package com.example.corebanking.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.corebanking.model.ActiveSession;
import com.example.corebanking.service.DepositService;

@ShellCommandGroup("CoreBanking Commands")
@ShellComponent
public class DepositCommand {

    private static final String NO_ACCOUNT_SELECTED = "no account selected. Select an account with the 'select' command.";

    @Autowired
    private ActiveSession activeSession;

    @Autowired
    private DepositService depositService;

    @ShellMethod("Deposit certain amount of money to the selected bank account.")
    public void deposit(int amount) {
        depositService.deposit(activeSession.getAccount(), amount);
    }

    public Availability depositAvailability() {
        return activeSession.getAccount() != null
                ? Availability.available()
                : Availability.unavailable(NO_ACCOUNT_SELECTED);
    }
}
