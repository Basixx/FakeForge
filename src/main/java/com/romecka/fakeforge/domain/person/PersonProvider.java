package com.romecka.fakeforge.domain.person;

import java.util.List;

public interface PersonProvider {

    List<PersonDto> getPersonsOfUser(Long userId,
                                     int page,
                                     int size);

}
