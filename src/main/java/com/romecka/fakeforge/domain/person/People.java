package com.romecka.fakeforge.domain.person;

import org.springframework.data.domain.Slice;

public interface People {

    Slice<Person> getPersonsOfUser(long userId,
                                   int page,
                                   int size);

    Person createPerson(long userId, PersonParams personParams);

}
