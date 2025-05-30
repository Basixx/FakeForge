package com.romecka.fakeforge.repository;

import com.romecka.fakeforge.domain.apikeys.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiKeyRepository extends JpaRepository<ApiKey, Long> {

}
