package com.example.corebanking.exception;

public class InvalidAmountException extends RuntimeException {

    private static final String INVALID_AMOUNT = "Invalid amount.";

    public InvalidAmountException() {
        super(INVALID_AMOUNT);
    }
}
