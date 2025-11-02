package com.romecka.fakeforge.infrastructure.db.user;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @NotNull Optional<UserEntity> findById(@NotNull Long id);

    @NotNull Optional<UserEntity> findByEmailAddress(@NotNull String email);

    boolean existsByEmailAddress(@NotNull String email);

    @NotNull Page<UserEntity> findAll(@NotNull Pageable pageable);

    default List<UserEntity> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return findAll(pageable).getContent();
    }

}
