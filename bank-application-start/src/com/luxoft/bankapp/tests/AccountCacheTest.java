package com.luxoft.bankapp.tests;

import static com.luxoft.bankapp.domain.types.AccountType.CHECKING;
import static com.luxoft.bankapp.domain.types.AccountType.SAVING;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import com.luxoft.bankapp.domain.AbstractAccount;
import com.luxoft.bankapp.domain.AccountCache;
import com.luxoft.bankapp.domain.CheckingAccount;

import org.junit.Test;

public class AccountCacheTest {

  @Test
  public void testCreateChecking() {
    AccountCache.load();

    AbstractAccount account = AccountCache.create(CHECKING);

    assertNotNull(account);
    assertThat(account, instanceOf(CheckingAccount.class));
    assertEquals(0, account.getId());
    assertEquals(0.0, account.getBalance());
    assertEquals(0.0, ((CheckingAccount) account).getOverdraft());
  }

  @Test
  public void testCreateSaving() {
    AccountCache.load();

    AbstractAccount account = AccountCache.create(SAVING);

    assertNotNull(account);
    assertThat(account, instanceOf(AbstractAccount.class));
    assertEquals(0, account.getId());
    assertEquals(0.0, account.getBalance());
  }
}
