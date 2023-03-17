package org.antwalk.ems.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Long> {
    
}
