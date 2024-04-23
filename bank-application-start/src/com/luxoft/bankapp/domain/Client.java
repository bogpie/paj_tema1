package com.luxoft.bankapp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client {

  private String name;
  private Gender gender;
  private List<Account> accounts = new ArrayList<>();

  public Client(String name, Gender gender) {
    this.name = name;
    this.gender = gender;
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

  public Gender getGender() {
    return gender;
  }

  public List<Account> getAccounts() {
    return Collections.unmodifiableList(accounts);
  }

  public String getClientGreeting() {
    if (gender != null) {
      return gender.getGreeting() + " " + name;
    } else {
      return name;
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGender(String gender) {
    this.gender = Gender.valueOf(gender);
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  @Override

  public String toString() {
    return getClientGreeting();
  }

}
