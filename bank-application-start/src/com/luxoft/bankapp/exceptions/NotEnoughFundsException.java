package com.luxoft.bankapp.exceptions;

import java.io.Serial;

public class NotEnoughFundsException extends BankException {

  @Serial
  private static final long serialVersionUID = -3034651278778929257L;
  private final int id;
  private final double balance;
  private final double amount;

  public NotEnoughFundsException(int id, double balance, double amount, String message) {
    super(message);
    this.id = id;
    this.balance = balance;
    this.amount = Math.round(amount * 100) / 100d;
  }

  public int getId() {
    return id;
  }

  public double getBalance() {
    return balance;
  }

  public double getAmount() {
    return amount;
  }

}
