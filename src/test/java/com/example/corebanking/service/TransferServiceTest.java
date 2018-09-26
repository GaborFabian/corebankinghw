package com.example.corebanking.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.corebanking.exception.AccountDoesNotExistException;
import com.example.corebanking.model.Account;
import com.example.corebanking.repository.AccountRepository;

public class TransferServiceTest {

    private static final long TO_ACCOUNT_ID = 11;
    private static final int AMOUNT = 100;
    private static final Account FROM_ACCOUNT = getTestAcount(66);
    private static final Account TO_ACCOUNT = getTestAcount(TO_ACCOUNT_ID);

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private WithdrawService withdrawService;

    @Mock
    private DepositService depositService;

    @InjectMocks
    private TransferService transferService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void transferShouldCallDepositWithdrawServices() {
        //GIVEN
        given(accountRepository.findById(TO_ACCOUNT_ID)).willReturn(Optional.of(TO_ACCOUNT));
        //WHEN
        transferService.transfer(FROM_ACCOUNT,(int) TO_ACCOUNT_ID, AMOUNT);
        //THEN
        verify(withdrawService).withdraw(FROM_ACCOUNT, AMOUNT);
        verify(depositService).deposit(TO_ACCOUNT, AMOUNT);
    }

    @Test(expected = AccountDoesNotExistException.class)
    public void transferShouldThrowExceptionIfToAccountDoesNotExist() {
        //GIVEN
        given(accountRepository.findById(TO_ACCOUNT_ID)).willReturn(Optional.empty());
        //WHEN
        transferService.transfer(FROM_ACCOUNT,(int) TO_ACCOUNT_ID, AMOUNT);
    }

    private static Account getTestAcount(long accountID) {
        Account account = new Account();
        account.setId(accountID);
        return account;
    }
}