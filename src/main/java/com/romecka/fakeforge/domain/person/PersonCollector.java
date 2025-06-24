package com.romecka.fakeforge.domain.person;

import java.util.List;

public interface PersonCollector {

    List<Person> getPersonsOfUser(Long userId,
                                  int page,
                                  int size);

    Person createPerson(Long userId);

}
