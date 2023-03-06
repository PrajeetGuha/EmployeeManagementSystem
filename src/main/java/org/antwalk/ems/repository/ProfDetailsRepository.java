package org.antwalk.ems.repository;

import org.antwalk.ems.model.ProfDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfDetailsRepository extends JpaRepository<ProfDetails,Long> {
    
}
