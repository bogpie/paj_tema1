package com.luxoft.bankapp.email;

import com.luxoft.bankapp.domain.Client;

import java.util.List;

public class Email {

  private final Client from;
  private final List<Client> to;
  private final List<Client> cc;
  private final String subject;
  private final String body;

  private Email(Builder builder) {
    this.from = builder.from;
    this.to = builder.to;
    this.cc = builder.cc;
    this.subject = builder.subject;
    this.body = builder.body;
  }

  @Override
  public String toString() {
    return "Email{" +
           "from=" + from +
           ", to=" + to +
           ", cc=" + cc +
           ", subject='" + subject + '\'' +
           ", body='" + body + '\'' +
           '}';
  }

  public static class Builder {

    private Client from;
    private List<Client> to;
    private List<Client> cc;
    private String subject;
    private String body;

    public Builder() {
      // Builder constructor
    }

    public final Email build() {
      return new Email(this);
    }

    public final Builder from(Client from) {
      this.from = from;
      return this;
    }

    public final Builder to(List<Client> to) {
      this.to = to;
      return this;
    }

    public final Builder cc(List<Client> cc) {
      this.cc = cc;
      return this;
    }

    public final Builder subject(String subject) {
      this.subject = subject;
      return this;
    }

    public final Builder body(String body) {
      this.body = body;
      return this;
    }
  }

}
