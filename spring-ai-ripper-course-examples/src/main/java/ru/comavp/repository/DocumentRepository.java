package ru.comavp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.comavp.model.LoadedDocument;

public interface DocumentRepository extends JpaRepository<LoadedDocument, Long> {

    boolean existsByFilenameAndContentHash(String filename, String contentHash);
}
