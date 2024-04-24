package com.luxoft.bankapp.domain;


public class SavingAccount extends AbstractAccount {

  public SavingAccount(int id, double balance) {
    super(id, balance);
  }

  @Override
  public String toString() {
    return "SavingAccount{" +
           "id=" + getId() +
           ", balance=" + getBalance() +
           '}';
  }

  public double maximumAmountToWithdraw() {
    return getBalance();
  }

}
