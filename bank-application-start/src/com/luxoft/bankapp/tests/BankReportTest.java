package com.luxoft.bankapp.tests;

import static org.junit.Assert.assertEquals;

import com.luxoft.bankapp.domain.Account;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.CheckingAccount;
import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.domain.Gender;
import com.luxoft.bankapp.domain.SavingAccount;
import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.report.BankReport;
import com.luxoft.bankapp.report.BankReportInterface;
import com.luxoft.bankapp.report.BankReportStreams;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class BankReportTest {

  private final BankReportInterface bankReport;
  private Bank bank;

  public BankReportTest(BankReportInterface bankReport) {
    this.bankReport = bankReport;
  }

  @Parameterized.Parameters
  public static List<BankReportInterface> data() {
    return Arrays.asList(
        new BankReport(),
        new BankReportStreams()
    );
  }

  @Before
  public void setUp() {
    bank = new Bank();

    // Add clients and accounts to the bank
    Client client1 = new Client(
        "John Doe",
        Gender.MALE,
        "Atlanta"
    );
    Account savingAccount1 = new SavingAccount(1, 1000);
    Account checkingAccount1 = new CheckingAccount(2, 2000, 500);
    client1.addAccount(savingAccount1);
    client1.addAccount(checkingAccount1);

    Client client2 = new Client(
        "Jane Doe",
        Gender.FEMALE,
        "Boston"
    );
    Account savingAccount2 = new SavingAccount(3, 3000);
    Account checkingAccount2 = new CheckingAccount(4, 4000, 1000);

    client2.addAccount(savingAccount2);
    client2.addAccount(checkingAccount2);

    try {
      bank.addClient(client1);
      bank.addClient(client2);
    } catch (ClientExistsException e) {
      System.out.println("Client already exists");
    }
  }

  @Test
  public void testGetNumberOfClients() {
    assertEquals(2, bankReport.getNumberOfClients(bank));
  }

  @Test
  public void testGetNumberOfAccounts() {
    assertEquals(
        "Bank should have 4 accounts",
        4,
        bankReport.getNumberOfAccounts(bank)
    );
  }

  @Test
  public void testGetClientsSorted() {
    var expected = List.of("Jane Doe", "John Doe");
    assertEquals(
        "Clients should be sorted by name",
        expected,
        bankReport.getClientsSorted(bank).stream().map(Client::getName).toList()
    );
  }

  @Test
  public void testGetTotalSumInAccounts() {
    assertEquals(
        "Total sum in accounts should be 10000",
        10000,
        bankReport.getTotalSumInAccounts(bank),
        0.0
    );
  }

  @Test
  public void testGetClientsByCity() {
    var expected = List.of("John Doe");
    assertEquals(
        "Client in Atlanta should be John Doe",
        expected,
        bankReport
            .getClientsByCity(bank)
            .get("Atlanta")
            .stream()
            .map(Client::getName)
            .toList()
    );

    expected = List.of("Jane Doe");
    assertEquals(
        "Client in Boston should be Jane Doe",
        expected,
        bankReport
            .getClientsByCity(bank)
            .get("Boston")
            .stream()
            .map(Client::getName)
            .toList()
    );
  }
}
