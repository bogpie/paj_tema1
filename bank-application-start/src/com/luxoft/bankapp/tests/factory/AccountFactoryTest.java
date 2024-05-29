package com.luxoft.bankapp.tests.factory;

import static com.luxoft.bankapp.domain.types.AccountType.CHECKING;
import static com.luxoft.bankapp.domain.types.AccountType.SAVING;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;
import com.luxoft.bankapp.factory.AccountFactory;

import org.junit.Test;

public class AccountFactoryTest {

  @Test
  public void testOfChecking() {
    Account account = AccountFactory.of(CHECKING);
    assertThat(account, instanceOf(CheckingAccount.class));
    assertEquals(0, account.getId());
    assertEquals(0.0, account.getBalance());
    assertEquals(0.0, ((CheckingAccount) account).getOverdraft());
  }

  @Test
  public void testOfSaving() {
    Account account = AccountFactory.of(SAVING);
    assertThat(account, instanceOf(SavingAccount.class));
    assertEquals(0, account.getId());
    assertEquals(0.0, account.getBalance());
  }
}
