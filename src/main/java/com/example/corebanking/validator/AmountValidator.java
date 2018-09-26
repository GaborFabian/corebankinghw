package com.example.corebanking.validator;

import org.springframework.stereotype.Component;

import com.example.corebanking.exception.InvalidAmountException;

@Component
public class AmountValidator {

    public boolean isValidAmount(int amount) {
        if(amount <= 0) {
            throw new InvalidAmountException();
        }
        return true;
    }

}
