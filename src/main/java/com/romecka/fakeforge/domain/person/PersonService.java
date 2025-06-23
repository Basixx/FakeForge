package com.romecka.fakeforge.domain.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonCollector personCollector;

    public List<PersonDto> getPersonsFromUser(Long userId,
                                              int page,
                                              int size) {
        return personCollector.getPersonsOfUser(
                userId,
                page,
                size
        );
    }

}
