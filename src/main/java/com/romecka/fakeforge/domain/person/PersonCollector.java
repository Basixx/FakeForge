package com.romecka.fakeforge.domain.person;

import java.util.List;

public interface PersonCollector {

    List<PersonDto> getPersonsOfUser(Long userId,
                                     int page,
                                     int size);

    PersonDto createPerson(Long userId);

}
