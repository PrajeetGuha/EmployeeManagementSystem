package org.antwalk.ems.repository;

import java.util.List;

import org.antwalk.ems.model.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Long> {
    @Query("select ed.emailId from EmployeeDetails ed")
    public List<String> listOfEmails();
}
