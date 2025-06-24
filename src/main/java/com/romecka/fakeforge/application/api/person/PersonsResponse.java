package com.romecka.fakeforge.application.api.person;

import com.romecka.fakeforge.domain.person.Person;

import java.util.List;

public record PersonsResponse(List<PersonResponse> persons) {

    public static PersonsResponse of(List<Person> persons) {
        List<PersonResponse> personResponses = persons
                .stream()
                .map(PersonResponse::of)
                .toList();
        return new PersonsResponse(personResponses);
    }

}
