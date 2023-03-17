package org.antwalk.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Long> {
    
}
