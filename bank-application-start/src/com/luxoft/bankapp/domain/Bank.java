package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.email.Email;
import com.luxoft.bankapp.email.EmailService;
import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.EmailException;
import com.luxoft.bankapp.utils.ClientRegistrationListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bank {

  // Task 1.1: Clients as set
  private final Set<Client> clients = new HashSet<>();
  private final List<ClientRegistrationListener> listeners = new ArrayList<>();
  private EmailService emailService;
  private int printedClients = 0;
  private int emailedClients = 0;
  private int debuggedClients = 0;

  private Client admin = new Client("Admin", Gender.MALE);

  private Client client = new Client("Client", Gender.MALE);

  public Bank() {
    listeners.add(client -> System.out.println("Client added: " + client.getName()));

    listeners.add(
        client -> {
          System.out.println("Notification email for client " + client.getName() + " is to be sent");

          if (emailService != null) {
            emailService.sendNotificationEmail(
                new Email.Builder()
                    .body("Welcome to our bank!")
                    .subject("Welcome!")
                    .to(List.of(client))
                    .from(admin)
                    .build()
            );
          }
        }
    );
    listeners.add(new DebugListener());

    listeners.add(
        // debug
        client -> {
          System.out.println(
              "Client " + client.getName()
              + " added on: " + DateFormat.getDateInstance(DateFormat.FULL).format(new Date())
          );
        }
    );

    emailService = new EmailService();
  }

  public EmailService getEmailService() {
    return emailService;
  }

  public int getPrintedClients() {
    return printedClients;
  }

  public int getEmailedClients() {
    return emailedClients;
  }

  public int getDebuggedClients() {
    return debuggedClients;
  }

  public void addClient(final Client client) throws ClientExistsException, EmailException {
    if (clients.contains(client)) {
      throw new ClientExistsException("Client already exists into the bank");
    }

    clients.add(client);
    notify(client);
  }

  private void notify(Client client) throws EmailException {
    for (ClientRegistrationListener listener : listeners) {
      listener.onClientAdded(client);
    }
  }

  public Set<Client> getClients() {
    return Collections.unmodifiableSet(clients);
  }

  class PrintClientListener implements ClientRegistrationListener {

    @Override
    public void onClientAdded(Client client) {
      System.out.println("Client added: " + client.getName());
      printedClients++;
    }

  }

  class EmailNotificationListener implements ClientRegistrationListener {

    @Override
    public void onClientAdded(Client client) {
      System.out.println("Notification email for client " + client.getName() + " to be sent");
      emailedClients++;
    }
  }

  class DebugListener implements ClientRegistrationListener {

    @Override
    public void onClientAdded(Client client) {
      System.out.println("Client " + client.getName() + " added on: " + DateFormat.getDateInstance(DateFormat.FULL).format(new Date()));
      debuggedClients++;
    }
  }

}




