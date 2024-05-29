package com.luxoft.bankapp.tests.factory;

import static com.luxoft.bankapp.domain.Gender.FEMALE;
import static com.luxoft.bankapp.domain.Gender.MALE;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

import com.luxoft.bankapp.domain.AbstractGender;
import com.luxoft.bankapp.domain.Female;
import com.luxoft.bankapp.domain.Male;
import com.luxoft.bankapp.factory.GenderFactory;

import org.junit.Test;

public class GenderFactoryTest {

  @Test
  public void testOfMale() {
    AbstractGender male = GenderFactory.of(MALE);
    assertThat(male, instanceOf(Male.class));
    assertEquals("Mr.", male.getGreeting());
  }

  @Test
  public void testOfFemale() {
    AbstractGender female = GenderFactory.of(FEMALE);
    assertThat(female, instanceOf(Female.class));
    assertEquals("Ms.", female.getGreeting());
  }

}
