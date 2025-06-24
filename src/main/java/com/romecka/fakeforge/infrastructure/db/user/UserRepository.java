package com.romecka.fakeforge.infrastructure.db.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @NotNull Optional<UserEntity> findById(@NotNull Long id);

}
