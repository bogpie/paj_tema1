package com.luxoft.bankapp.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Client {

  private final String name;
  private final Gender gender;
  private final String city;
  private final Set<Account> accounts = new HashSet<>();

  public Client(String name, Gender gender) {
    this.name = name;
    this.gender = gender;
    this.city = "Unknown";
  }

  public Client(String name, Gender gender, String city) {
    this.name = name;
    this.gender = gender;
    this.city = city;
  }

  @Override
  public boolean equals(Object obj) {
    return
        obj instanceof Client client &&
        name.equals(client.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  public void addAccount(final Account account) {
    accounts.add(account);
  }

  public String getName() {
    return name;
  }

  public Set<Account> getAccounts() {
    return Collections.unmodifiableSet(accounts);
  }

  public String getClientGreeting() {
    if (gender != null) {
      return gender.getGreeting() + " " + name;
    } else {
      return name;
    }
  }

  public String getCity() {
    return city;
  }

  @Override
  public String toString() {
    return getClientGreeting();
  }

}
