package com.romecka.fakeforge.infrastructure.db.limit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {

    Optional<LimitEntity> findByUserId(long userId);

    @Modifying
    @Query("UPDATE LimitEntity l SET l.availableLimit = l.dailyLimit")
    void resetAllLimits();

}
