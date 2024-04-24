package com.luxoft.bankapp.tests;

import static org.junit.Assert.assertEquals;

import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.domain.Gender;
import com.luxoft.bankapp.email.Email;
import com.luxoft.bankapp.email.EmailService;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

@SuppressWarnings("CallToPrintStackTrace")
public class EmailServiceTest {

  private static final int NO_EMAILS = 7;
  private String body;
  private String subject;
  private List<Client> to;
  private List<Client> cc;
  private Client from;

  @Before
  public void setUp() {
    body = "Test body";
    subject = "Test subject";
    from = new Client("Adam", Gender.MALE, "New York");

    to = List.of(
        new Client("John", Gender.MALE, "Atlanta")
    );
    cc = List.of(
        new Client("Jane", Gender.FEMALE, "Boston")
    );
  }

  @Test
  public void testSendNotificationEmail() {
    EmailService emailService = new EmailService();

    for (int i = 0; i < NO_EMAILS; i++) {
      var email = new Email.Builder()
          .body(body + i)
          .subject(subject + i)
          .to(to)
          .cc(cc)
          .from(from)
          .build();
      emailService.sendNotificationEmail(
          email
      );

      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
        Thread.currentThread().interrupt();
      }
    }

    assertEquals(
        "Emails sent should be equal to NO_EMAILS",
        NO_EMAILS,
        emailService.getCount()
    );
  }
}
