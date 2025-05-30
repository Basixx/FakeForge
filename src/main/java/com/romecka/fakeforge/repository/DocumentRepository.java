package com.romecka.fakeforge.repository;

import com.romecka.fakeforge.domain.person.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
