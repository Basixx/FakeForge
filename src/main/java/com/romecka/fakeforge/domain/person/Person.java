package com.romecka.fakeforge.domain.person;

public interface Person {

    String name();

    String lastName();

    String emailAddress();

    String phoneNumber();

    String personalId();

    Gender gender();

    String citizenship();

    String bankAccountNumber();

    Document document();

    Address address();

}
