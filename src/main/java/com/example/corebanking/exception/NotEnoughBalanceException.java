package com.example.corebanking.exception;

public class NotEnoughBalanceException extends RuntimeException {

    private static final String NOT_ENOUGH_BALANCE = "Not enough balance for this action.";

    public NotEnoughBalanceException() {
        super(NOT_ENOUGH_BALANCE);
    }
}
