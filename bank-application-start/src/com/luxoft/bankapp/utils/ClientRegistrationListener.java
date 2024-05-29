package com.luxoft.bankapp.utils;

import com.luxoft.bankapp.domain.Client;
import com.luxoft.bankapp.exceptions.EmailException;

public interface ClientRegistrationListener {

  void onClientAdded(Client client) throws EmailException;
}
