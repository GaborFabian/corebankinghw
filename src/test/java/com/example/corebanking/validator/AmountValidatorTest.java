package com.example.corebanking.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.example.corebanking.exception.InvalidAmountException;

public class AmountValidatorTest {

    private AmountValidator amountValidator;

    @Before
    public void setUp() {
        amountValidator = new AmountValidator();
    }

    @Test
    public void isValidShouldReturnTrue() {
        //GIVEN
        int amount = 100;
        //WHEN
        boolean actual = amountValidator.isValidAmount(amount);
        //THEN
        assertTrue(actual);
    }

    @Test(expected = InvalidAmountException.class)
    public void isValidShouldThrowInvalidAmountExceptionIfAmountIsZero() {
        //GIVEN
        int amount = 0;
        //WHEN
        amountValidator.isValidAmount(amount);
    }

    @Test(expected = InvalidAmountException.class)
    public void isValidShouldThrowInvalidAmountExceptionIfAmountIsNegative() {
        //GIVEN
        int amount = -100;
        //WHEN
        amountValidator.isValidAmount(amount);
    }

}