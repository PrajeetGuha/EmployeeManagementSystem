package org.antwalk.ems.repository;

import java.util.List;

import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.QualificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationDetailsRepository extends JpaRepository<QualificationDetails,Long> {

    
}
