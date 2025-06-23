package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.PersonDto;

import java.util.List;

public record PersonResponse(List<PersonDto> persons) {

    public static PersonResponse fromPerson(List<PersonDto> persons) {
        return new PersonResponse(
                persons
        );
    }

}
