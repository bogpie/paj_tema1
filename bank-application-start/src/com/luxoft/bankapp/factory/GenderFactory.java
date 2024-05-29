package com.luxoft.bankapp.factory;

import com.luxoft.bankapp.domain.AbstractGender;
import com.luxoft.bankapp.domain.Female;
import com.luxoft.bankapp.domain.Gender;
import com.luxoft.bankapp.domain.Male;

public class GenderFactory {

  private GenderFactory() {
  }

  public static AbstractGender of(Gender genderType) {
    return switch (genderType) {
      case MALE -> new Male();
      case FEMALE -> new Female();
    };
  }

}
