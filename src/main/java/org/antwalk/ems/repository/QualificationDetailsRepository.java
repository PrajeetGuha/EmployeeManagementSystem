package org.antwalk.ems.repository;

import org.antwalk.ems.model.QualificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationDetailsRepository extends JpaRepository<QualificationDetails,Long> {
    
}
