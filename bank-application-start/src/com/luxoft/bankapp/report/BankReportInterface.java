package com.luxoft.bankapp.report;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public interface BankReportInterface {

  /**
   * Returns the number of bank clients.
   */
  int getNumberOfClients(Bank bank);

  /**
   * Returns the total number of accounts in the bank.
   */
  int getNumberOfAccounts(Bank bank);

  /**
   * Displays the set of clients in alphabetical
   * order.
   */
  SortedSet<Client> getClientsSorted(Bank bank);

  /**
   * Returns the total sum (balance) from the
   * accounts of all bank clients.
   */
  double getTotalSumInAccounts(Bank bank);

  /**
   * Returns the set of all accounts. The list is
   * ordered by current account balance.
   */
  SortedSet<Account> getAccountsSortedBySum(Bank bank);

  /**
   * Returns the total amount of credits granted to
   * the bank clients. That is, the sum of all values
   * above account balance for
   * CheckingAccount
   */
  double getBankCreditSum(final Bank bank);

  /**
   * Returns a map
   * Client<=>List_of_His accounts. This method is somehow
   * ‘artificial’, because a client already has a list
   * of his/her accounts. The aim of this step is to
   * learn to declare complex data structures
   * using generics and convert data
   */
  Map<Client, Collection<Account>> getCustomerAccounts(final Bank bank);

  /**
   * Add field city to class Client. This
   * method needs a table Map<String,
   * List<Client>>, with cities as the keys
   * and values – the list of clients in each city.
   * Print the resulting table, and order by city
   * name alphabetically.
   */
  Map<String, List<Client>> getClientsByCity(final Bank bank);
}

