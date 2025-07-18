package com.romecka.fakeforge.infrastructure.db.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Page<PersonEntity> findByUserId(long userId, Pageable pageable);

    default List<PersonEntity> findByUserId(long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return findByUserId(userId, pageable).getContent();
    }

}
