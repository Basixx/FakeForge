package com.romecka.fakeforge.infrastructure.db.user;

import com.romecka.fakeforge.domain.user.DocumentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "documents")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Document {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, name = "document_id")
    Long id;

    @NotNull
    DocumentType type;

    @Column(length = 10)
    @NotNull
    String number;

}
