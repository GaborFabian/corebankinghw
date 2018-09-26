package com.example.corebanking.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.corebanking.model.Account;
import com.example.corebanking.model.ActiveSession;
import com.example.corebanking.repository.AccountRepository;

public class SelectAccountCommandTest {

    private static final long EXISTING_ACCOUNT_ID = 100;
    private static final long NOT_EXISTING_ACCOUNT_ID = 999;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ActiveSession activeSession;

    @InjectMocks
    private SelectAccountCommand selectAccountCommand;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void selectShouldSetTheActiveAccount() {
        //GIVEN
        Account existingAccount = new Account();
        existingAccount.setId(EXISTING_ACCOUNT_ID);
        given(accountRepository.findById(EXISTING_ACCOUNT_ID)).willReturn(Optional.of(existingAccount));
        //WHEN
        selectAccountCommand.select((int) EXISTING_ACCOUNT_ID);
        //THEN
        verify(activeSession).setAccount(existingAccount);
    }

    @Test
    public void selectShouldNotSetTheActiveAccountWhenAccountDoesNotExist() {
        //GIVEN
        given(accountRepository.findById(NOT_EXISTING_ACCOUNT_ID)).willReturn(Optional.empty());
        //WHEN
        selectAccountCommand.select((int) NOT_EXISTING_ACCOUNT_ID);
        //THEN
        verify(activeSession, never()).setAccount(any());
    }

}