package org.antwalk.ems.repository;

import org.antwalk.ems.model.FamilyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyDetailsRepository extends JpaRepository<FamilyDetails,Long> {
    
}
