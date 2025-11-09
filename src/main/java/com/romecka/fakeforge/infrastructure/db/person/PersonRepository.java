package com.romecka.fakeforge.infrastructure.db.person;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Slice<PersonEntity> findByUserIdOrderByIdAsc(long userId, Pageable pageable);

    default Slice<PersonEntity> findByUserId(long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return findByUserIdOrderByIdAsc(userId, pageable);
    }

}
