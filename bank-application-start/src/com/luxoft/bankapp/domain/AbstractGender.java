package com.luxoft.bankapp.domain;

public class AbstractGender {

  private final String greeting;

  protected AbstractGender(String greeting) {
    this.greeting = greeting;
  }

  public String getGreeting() {
    return greeting;
  }

}
