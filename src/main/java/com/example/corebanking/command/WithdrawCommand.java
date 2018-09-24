package com.example.corebanking.command;

import com.example.corebanking.exception.InvalidAmountException;
import com.example.corebanking.exception.NotEnoughBalanceException;
import com.example.corebanking.model.ActiveSession;
import com.example.corebanking.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellCommandGroup("CoreBanking Commands")
@ShellComponent
public class WithdrawCommand {

    private static final String NO_ACCOUNT_SELECTED = "no account selected. Select an account with the 'select' command.";
    private static final String NOT_ENOUGH_BALANCE = "Not enough balance for this withdrawal.";
    private static final String INVALID_AMOUNT = "Invalid amount.";

    @Autowired
    private ActiveSession activeSession;

    @Autowired
    private WithdrawService withdrawService;

    @ShellMethod("Withdraw certain amount of money from the selected bank account.")
    public void withdraw(int amount) {
        try {
            withdrawService.withdraw(activeSession.getAccount(), amount);
        } catch (NotEnoughBalanceException e) {
            System.out.println(NOT_ENOUGH_BALANCE);
        } catch (InvalidAmountException e) {
            System.out.println(INVALID_AMOUNT);
        }
    }

    public Availability withdrawAvailability() {
        return activeSession.getAccount() != null
                ? Availability.available()
                : Availability.unavailable(NO_ACCOUNT_SELECTED);
    }
}
