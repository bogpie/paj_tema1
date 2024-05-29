package com.luxoft.bankapp.exceptions;

import java.io.Serial;

public class OverdraftLimitExceededException extends NotEnoughFundsException {

  @Serial
  private static final long serialVersionUID = -3737648528527468343L;
  private final double overdraft;

  public OverdraftLimitExceededException(NotEnoughFundsException e, double overdraft) {
    super(e.getId(), e.getBalance(), e.getAmount(), e.getMessage());
    this.overdraft = overdraft;
  }

  public double getOverdraft() {
    return overdraft;
  }

}
