package com.romecka.fakeforge.domain.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonProvider personProvider;

    public List<PersonDto> getPersonsFromUser(Long userId,
                                              int page,
                                              int size) {
        return personProvider.getPersonsOfUser(
                userId,
                page,
                size
        );
    }

}
