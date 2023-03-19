package org.antwalk.ems.repository;

import java.util.List;

import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.FamilyDetails;
import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.ProfDetails;
import org.antwalk.ems.model.QualificationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Long> {
    @Query("select ed.emailId from EmployeeDetails ed")
    public List<String> listOfEmails();
    
    @Query("select listQualificationDetails from EmployeeDetails ed where ed.empDetId = :id")
    public List<QualificationDetails> findQualificationDetailsByEmpDetId(Long id);

    @Query("select listProfDetails from EmployeeDetails ed where ed.empDetId = :id")
    public List<ProfDetails> findProfessionalDetailsByEmpDetId(Long id);

    @Query("select listFamilyDetails from EmployeeDetails ed where ed.empDetId = :id")
    public List<FamilyDetails> findFamilyDetailsByEmpDetId(Long id);
    
}
