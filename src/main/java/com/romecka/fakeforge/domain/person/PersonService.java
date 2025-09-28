package com.romecka.fakeforge.domain.person;

import com.romecka.fakeforge.domain.limit.Limits;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonService {

    private final People people;

    private final Limits limits;

    public List<Person> getPersonsFromUser(long userId, int page, int size) {
        return people.getPersonsOfUser(
                userId,
                page,
                size
        );
    }

    public Person createPerson(long userId, PersonParams personParams) {
        limits.useLimit(userId);
        return people.createPerson(userId, personParams);
    }

}
