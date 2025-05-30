package com.romecka.fakeforge.repository;

import com.romecka.fakeforge.domain.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Page<Person> findByUserId(Long userId, Pageable pageable);

    default List<Person> findByUserId(Long userId,
                                      int page,
                                      int size) {
        Pageable pageable = PageRequest.of(page, size);
        return findByUserId(userId, pageable).getContent();
    }

}
