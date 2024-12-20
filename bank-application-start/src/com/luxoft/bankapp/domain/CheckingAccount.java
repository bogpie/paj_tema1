package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverdraftLimitExceededException;

public class CheckingAccount extends AbstractAccount {

  private final double overdraft;

  public CheckingAccount() {
    super(0, 0);
    overdraft = 0;
  }

  public CheckingAccount(int id, double balance, double overdraft) {
    super(id, balance);
    if (overdraft < 0) {
      throw new IllegalArgumentException("Cannot create an account with a starting negative overdraft");
    }
    this.overdraft = overdraft;
  }

  @Override
  public String toString() {
    return "CheckingAccount{" +
           "id=" + getId() +
           ", balance=" + getBalance() +
           ", overdraft=" + overdraft +
           '}';
  }

  @Override
  public void withdraw(double value) throws OverdraftLimitExceededException {
    try {
      super.withdraw(value);
    } catch (NotEnoughFundsException notEnoughFundsException) {
      throw new OverdraftLimitExceededException(notEnoughFundsException, overdraft);
    }
  }

  public double getOverdraft() {
    return overdraft;
  }

  public double maximumAmountToWithdraw() {
    return getBalance() + overdraft;
  }


  @Override
  public boolean equals(Object obj) {
    return
        obj instanceof Account account &&
        super.equals(account) &&
        overdraft == ((CheckingAccount) account).overdraft;
  }

  @Override
  public int hashCode() {
    return super.hashCode() + Double.hashCode(overdraft);
  }
}
