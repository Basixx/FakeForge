package com.romecka.fakeforge.domain.person;

import java.util.List;

public interface People {

    List<Person> getPersonsOfUser(long userId,
                                  int page,
                                  int size);

    Person createPerson(long userId, PersonParams personParams);

}
