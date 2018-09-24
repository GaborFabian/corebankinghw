package com.example.corebanking.model;

import org.springframework.stereotype.Component;

@Component
public class ActiveSession {

    public Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
