package com.luxoft.bankapp.email;

import com.luxoft.bankapp.exceptions.EmailException;

@SuppressWarnings("CallToPrintStackTrace")
public class EmailService {

  public static final Object lock = new Object();
  private final Queue queue = new Queue();
  private final Thread thread;
  private volatile boolean running = true;
  private int count;

  public EmailService() {
    thread = new Thread(
        () -> {
          while (running) {
            synchronized (lock) {
              var email = queue.pop();
              email.ifPresent(this::sendEmail);
              try {
                lock.wait(20000);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
              }
            }
          }
        }
    );

    thread.start();
  }

  public int getCount() {
    return count;
  }

  private void sendEmail(Email email) {
    System.out.println("Sending email: " + email);
    ++count;
  }

  public void sendNotificationEmail(Email email) throws EmailException {
    synchronized (lock) {
      if (running) {
        queue.add(email);
        lock.notifyAll();
      } else {
        throw new EmailException("Email service is not running");
      }
    }
  }

  public void close() {
    running = false;
    synchronized (lock) {
      lock.notifyAll();
    }
    try {
      thread.join();
    } catch (InterruptedException e) {
      thread.interrupt();
    }

  }
}
