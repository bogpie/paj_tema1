package com.luxoft.bankapp.domain;

import static com.luxoft.bankapp.domain.types.AccountType.CHECKING;
import static com.luxoft.bankapp.domain.types.AccountType.SAVING;
import static com.luxoft.bankapp.factory.AccountFactory.of;

import com.luxoft.bankapp.domain.types.AccountType;

import java.util.HashMap;
import java.util.Map;

public class AccountCache {

  private static final Map<String, AbstractAccount> cache = new HashMap<>();

  private AccountCache() {
  }

  public static void load() {
    cache.put(CHECKING.name(), of(CHECKING));
    cache.put(SAVING.name(), of(SAVING));
  }

  public static AbstractAccount create(AccountType type) {
    return cache.get(type.name()).clone();
  }

}
