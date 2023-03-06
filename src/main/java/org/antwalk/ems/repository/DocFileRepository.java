package org.antwalk.ems.repository;

import org.antwalk.ems.model.DocFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocFileRepository extends JpaRepository<DocFile,Long> {
    
}
