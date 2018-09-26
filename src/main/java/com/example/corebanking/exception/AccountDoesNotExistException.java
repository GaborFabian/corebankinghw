package com.example.corebanking.exception;

public class AccountDoesNotExistException extends RuntimeException {

    private static final String ACCOUNT_DOES_NOT_EXIST = "The account does not exist: ";

    public AccountDoesNotExistException(int requestedAccountID) {
        super(ACCOUNT_DOES_NOT_EXIST + requestedAccountID);
    }
}
