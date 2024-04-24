package com.luxoft.bankapp.report;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BankReportStreams implements BankReportInterface {

  @Override
  public int getNumberOfClients(Bank bank) {
    return bank
        .getClients()
        .stream()
        .map(Client::getName)
        .collect(Collectors.toSet())
        .size();
  }

  @Override
  public int getNumberOfAccounts(Bank bank) {
    return bank
        .getClients()
        .stream()
        .map(Client::getAccounts)
        .mapToInt(Collection::size)
        .sum();
  }

  @Override
  public SortedSet<Client> getClientsSorted(Bank bank) {
    return bank
        .getClients()
        .stream()
        .collect(
            Collectors.toCollection(
                () -> new TreeSet<>(
                    Comparator.comparing(Client::getName)
                )
            )
        );
  }

  @Override
  public double getTotalSumInAccounts(Bank bank) {
    return bank
        .getClients()
        .stream()
        .map(Client::getAccounts)
        .flatMap(Collection::stream)
        .mapToDouble(Account::getBalance)
        .sum();
  }

  @Override
  public SortedSet<Account> getAccountsSortedBySum(Bank bank) {
    return bank
        .getClients()
        .stream()
        .map(Client::getAccounts)
        .flatMap(Collection::stream)
        .collect(
            Collectors.toCollection(
                () -> new TreeSet<>(
                    Comparator.comparing(Account::getBalance)
                )
            )
        );
  }

  @Override
  public double getBankCreditSum(Bank bank) {
    return bank
        .getClients()
        .stream()
        .map(Client::getAccounts)
        .flatMap(Collection::stream)
        .filter(account -> account instanceof CheckingAccount)
        .mapToDouble(account -> ((CheckingAccount) account).getOverdraft())
        .sum();
  }

  @Override
  public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {
    return bank
        .getClients()
        .stream()
        .collect(
            Collectors.toMap(
                client -> client,
                Client::getAccounts
            )
        );
  }

  @Override
  public Map<String, List<Client>> getClientsByCity(Bank bank) {
    return bank
        .getClients()
        .stream()
        .collect(
            Collectors.groupingBy(
                Client::getCity
            )
        );
  }
}
