package com.romecka.fakeforge.infrastructure.db.limit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {

    Optional<LimitEntity> findByUserId(Long userId);

}
