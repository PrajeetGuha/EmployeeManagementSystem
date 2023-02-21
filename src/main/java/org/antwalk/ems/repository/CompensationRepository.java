package org.antwalk.ems.repository;

import org.antwalk.ems.model.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation,Long> {
    
}
