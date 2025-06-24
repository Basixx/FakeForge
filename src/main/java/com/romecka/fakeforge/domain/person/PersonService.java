package com.romecka.fakeforge.domain.person;

import com.romecka.fakeforge.domain.limit.LimitCollector;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonCollector personCollector;

    private final LimitCollector limitCollector;

    public List<Person> getPersonsFromUser(Long userId,
                                           int page,
                                           int size) {
        return personCollector.getPersonsOfUser(
                userId,
                page,
                size
        );
    }

    public Person createPerson(Long userId) {
        limitCollector.useLimit(userId);
        return personCollector.createPerson(userId);
    }

}
