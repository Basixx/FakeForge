package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.PersonDto;

import java.util.List;

public record PersonsResponse(List<PersonResponse> persons) {

    public static PersonsResponse fromPerson(List<PersonDto> persons) {
        List<PersonResponse> personResponses = persons
                .stream()
                .map(PersonResponse::fromPerson)
                .toList();
        return new PersonsResponse(personResponses);
    }

}
