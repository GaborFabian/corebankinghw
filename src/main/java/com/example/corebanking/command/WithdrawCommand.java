package com.example.corebanking.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.example.corebanking.model.ActiveSession;
import com.example.corebanking.service.WithdrawService;

@ShellCommandGroup("CoreBanking Commands")
@ShellComponent
public class WithdrawCommand {

    private static final String NO_ACCOUNT_SELECTED = "no account selected. Select an account with the 'select' command.";

    @Autowired
    private ActiveSession activeSession;

    @Autowired
    private WithdrawService withdrawService;

    @ShellMethod("Withdraw certain amount of money from the selected bank account.")
    public void withdraw(int amount) {
        withdrawService.withdraw(activeSession.getAccount(), amount);
    }

    public Availability withdrawAvailability() {
        return activeSession.getAccount() != null
                ? Availability.available()
                : Availability.unavailable(NO_ACCOUNT_SELECTED);
    }
}
