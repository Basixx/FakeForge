package com.romecka.fakeforge.repository;

import com.romecka.fakeforge.domain.entities.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

}
