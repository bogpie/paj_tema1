package com.luxoft.bankapp.main;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.domain.Gender;
import com.luxoft.bankapp.domain.SavingAccount;
import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.EmailException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.bankapp.report.BankReport;
import com.luxoft.bankapp.service.BankService;

import java.util.Scanner;

@SuppressWarnings("CallToPrintStackTrace")
public class BankApplication {

  public static final String NOT_ENOUGH_FUNDS_FORMAT = "Not enough funds for account %d, balance: %.2f, tried to extract amount: %.2f%n";
  private static Bank bank;

  public static void main(String[] args) throws EmailException {
    bank = new Bank();
    modifyBank();
    printBalance();
    BankService.printMaximumAmountToWithdraw(bank);

    // Check if the -statistics argument is passed
    if (args.length > 0 && args[0].equals("-statistics")) {
      statisticsMode();
    }

    bank.getEmailService().close();
  }

  private static void statisticsMode() {
    Scanner scanner = new Scanner(System.in);
    System.out.println(
        "Entered statistics mode. "
        + "Type 'display statistic' to display statistics. "
        + "Type 'exit' or just enter twice to exit statistics mode."
    );

    while (true) {
      String command = scanner.nextLine();

      if ("display statistic".equals(command)) {
        BankReport bankReport = new BankReport();
        printBankReport(bankReport);
        return;
      } else if ("exit".equals(command) || command.isEmpty()) {
        System.out.println("Exiting statistics mode.");
        return;
      } else {
        System.out.println(
            "Unrecognized command. Type 'display statistic' to display statistics, "
            + "or 'exit' to exit statistics mode."
        );
      }
    }
  }

  private static void modifyBank() throws EmailException {
    Client client1 = new Client("John", Gender.MALE);
    Account account1 = new SavingAccount(1, 100);
    Account account2 = new CheckingAccount(2, 100, 20);
    client1.addAccount(account1);
    client1.addAccount(account2);

    try {
      BankService.addClient(bank, client1);
    } catch (ClientExistsException e) {
      System.out.format("Cannot add an already existing client: %s%n", client1.getName());
    } catch (EmailException e) {
      System.out.println("Email exception");
    }

    account1.deposit(100);
    try {
      account1.withdraw(10);
    } catch (OverdraftLimitExceededException e) {
      System.out.format("Not enough funds for account %d, balance: %.2f, overdraft: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getOverdraft(), e.getAmount());
    } catch (NotEnoughFundsException e) {
      System.out.format(NOT_ENOUGH_FUNDS_FORMAT, e.getId(), e.getBalance(), e.getAmount());
    }

    try {
      account2.withdraw(90);
    } catch (OverdraftLimitExceededException e) {
      System.out.format("Not enough funds for account %d, balance: %.2f, overdraft: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getOverdraft(), e.getAmount());
    } catch (NotEnoughFundsException e) {
      System.out.format(NOT_ENOUGH_FUNDS_FORMAT, e.getId(), e.getBalance(), e.getAmount());
    }

    try {
      account2.withdraw(100);
    } catch (OverdraftLimitExceededException e) {
      System.out.format("Not enough funds for account %d, balance: %.2f, overdraft: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getOverdraft(), e.getAmount());
    } catch (NotEnoughFundsException e) {
      System.out.format(NOT_ENOUGH_FUNDS_FORMAT, e.getId(), e.getBalance(), e.getAmount());
    }

    try {
      BankService.addClient(bank, client1);
    } catch (ClientExistsException e) {
      System.out.format("Cannot add an already existing client: %s%n", client1);
    }
  }

  private static void printBalance() {
    System.out.format("%nPrint balance for all clients%n");
    for (Client client : bank.getClients()) {
      System.out.println("Client: " + client);
      for (Account account : client.getAccounts()) {
        System.out.format("Account %d : %.2f%n", account.getId(), account.getBalance());
      }
    }
  }

  private static void printBankReport(BankReport bankReport) {
    System.out.format("Number of clients: %d%n", bankReport.getNumberOfClients(bank));
    System.out.println("Number of accounts: " + bankReport.getNumberOfAccounts(bank));
    System.out.println("Clients in alphabetical order." + bankReport.getClientsSorted(bank));
    System.out.println("Total sum in accounts: " + bankReport.getTotalSumInAccounts(bank));
    System.out.println("Accounts sorted by sum: " + bankReport.getAccountsSortedBySum(bank));
    System.out.println("Total credit granted: " + bankReport.getBankCreditSum(bank));
    System.out.println("Customer accounts: " + bankReport.getCustomerAccounts(bank));
    System.out.println("Clients by city: " + bankReport.getClientsByCity(bank));
  }
}
