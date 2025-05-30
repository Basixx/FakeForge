package com.romecka.fakeforge.service;

import com.romecka.fakeforge.domain.entities.Person;
import com.romecka.fakeforge.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public List<Person> getPersonsFromUser(Long userId,
                                           int page,
                                           int size) {
        return personRepository.findByUserId(
                userId,
                page,
                size
        );
    }

}
