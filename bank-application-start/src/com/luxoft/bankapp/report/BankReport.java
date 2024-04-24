package com.luxoft.bankapp.report;

import static java.util.Comparator.naturalOrder;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class BankReport implements BankReportInterface {

  /**
   * Returns the number of bank clients.
   */
  @Override
  public int getNumberOfClients(Bank bank) {
    return bank.getClients().size();
  }

  /**
   * Returns the total number of accounts in the bank.
   * No streams version.
   */
  @Override
  public int getNumberOfAccounts(Bank bank) {
    int total = 0;
    for (var client : bank.getClients()) {
      total += client.getAccounts().size();
    }
    return total;
  }

  /**
   * Displays the set of clients in alphabetical
   * order.
   */
  @Override
  public SortedSet<Client> getClientsSorted(final Bank bank) {
    SortedSet<Client> sortedClients = new TreeSet<>(
        Comparator.comparing(Client::getName)
    );

    sortedClients.addAll(bank.getClients());
    return sortedClients;
  }


  /**
   * Returns the total sum (balance) from the
   * accounts of all bank clients.
   */
  @Override
  public double getTotalSumInAccounts(Bank bank) {
    double total = 0;
    for (var client : bank.getClients()) {
      for (var account : client.getAccounts()) {
        total += account.getBalance();
      }
    }
    return total;
  }

  /**
   * Returns the set of all accounts. The list is
   * ordered by current account balance.
   */
  @Override
  public SortedSet<Account> getAccountsSortedBySum(Bank bank) {
    SortedSet<Account> sortedAccounts = new TreeSet<>(
        Comparator.comparingDouble(Account::getBalance)
    );

    for (var client : bank.getClients()) {
      sortedAccounts.addAll(client.getAccounts());
    }

    return sortedAccounts;
  }

  /**
   * Returns the total amount of credits granted to
   * the bank clients. That is, the sum of all values
   * above account balance for
   * CheckingAccount
   */
  @Override
  public double getBankCreditSum(final Bank bank) {
    double total = 0.0;

    for (var client : bank.getClients()) {
      for (var account : client.getAccounts()) {
        if (account instanceof CheckingAccount checkingAccount) {
          total += checkingAccount.getOverdraft();
        }
      }
    }

    return total;
  }

  /**
   * Returns a map
   * Client<=>List_of_His accounts. This method is somehow
   * ‘artificial’, because a client already has a list
   * of his/her accounts. The aim of this step is to
   * learn to declare complex data structures
   * using generics and convert data
   */
  @Override
  public Map<Client, Collection<Account>> getCustomerAccounts(final Bank bank) {
    Map<Client, Collection<Account>> accountsMap = new HashMap<>();

    for (var client : bank.getClients()) {
      accountsMap.put(
          client,
          client.getAccounts()
      );
    }

    return accountsMap;
  }

  /**
   * Add field city to class Client. This
   * method needs a table Map<String,
   * List<Client>>, with cities as the keys
   * and values – the list of clients in each city.
   * Print the resulting table, and order by city
   * name alphabetically.
   */
  @Override
  public Map<String, List<Client>> getClientsByCity(final Bank bank) {
    Map<String, List<Client>> cityToClients = new TreeMap<>(naturalOrder());

    for (var client : bank.getClients()) {
      List<Client> clientsForCity = cityToClients.getOrDefault(
          client.getCity(),
          new ArrayList<>()
      );

      clientsForCity.add(client);
      var city = client.getCity();
      cityToClients.put(city, clientsForCity);
    }

    return cityToClients;
  }
}
