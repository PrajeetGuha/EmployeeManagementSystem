package org.antwalk.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.antwalk.ems.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {
    
}
