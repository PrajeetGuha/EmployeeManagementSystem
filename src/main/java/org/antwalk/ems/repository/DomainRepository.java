package org.antwalk.ems.repository;

import org.antwalk.ems.model.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain,Long> {
    
}
