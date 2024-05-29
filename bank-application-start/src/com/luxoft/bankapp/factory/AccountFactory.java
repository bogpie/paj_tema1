package com.luxoft.bankapp.factory;

import com.luxoft.bankapp.domain.AbstractAccount;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.SavingAccount;
import com.luxoft.bankapp.domain.types.AccountType;

public class AccountFactory {

  private AccountFactory() {
  }

  public static AbstractAccount of(AccountType accountType) {
    return switch (accountType) {
      case SAVING -> new SavingAccount();
      case CHECKING -> new CheckingAccount();
    };
  }
}
