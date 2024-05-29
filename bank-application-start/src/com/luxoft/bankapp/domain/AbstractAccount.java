package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

public abstract class AbstractAccount implements Account, Cloneable {

  private final int id;

  protected double balance;

  protected AbstractAccount(int id, double balance) {
    this.id = id;
    this.balance = balance;
  }

  @Override
  public void deposit(final double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Cannot deposit a negative amount");
    }
    this.balance += amount;
  }

  @Override
  public void withdraw(final double amount) throws NotEnoughFundsException {
    if (amount < 0) {
      throw new IllegalArgumentException("Cannot withdraw a negative amount");
    }

    if (amount > maximumAmountToWithdraw()) {
      throw new NotEnoughFundsException(id, balance, amount, "Requested amount exceeds the maximum amount to withdraw");
    }

    this.balance -= amount;
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public double getBalance() {
    return balance;
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof Account account &&
           id == account.getId();
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public AbstractAccount clone() {
    try {
      return (AbstractAccount) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
