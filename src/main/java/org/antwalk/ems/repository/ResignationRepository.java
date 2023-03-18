package org.antwalk.ems.repository;

import org.antwalk.ems.model.Resignation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResignationRepository extends JpaRepository<Resignation,Long> {
@Query("from Resignation")
    public Page<Resignation> findAllRecentResignations(Pageable pageable);
    
}
