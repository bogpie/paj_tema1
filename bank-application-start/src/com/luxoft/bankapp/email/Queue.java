package com.luxoft.bankapp.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Queue {

  private final List<Email> emails;

  public Queue(List<Email> emails) {
    this.emails = emails;
  }

  public Queue() {
    this.emails = new ArrayList<>();
  }

  public Optional<Email> pop() {
    if (emails.isEmpty()) {
      return Optional.empty();
    }

    Email email = emails.get(0);
    emails.remove(0);

    return Optional.ofNullable(email);
  }

  public void add(Email email) {
    emails.add(email);
  }
}
