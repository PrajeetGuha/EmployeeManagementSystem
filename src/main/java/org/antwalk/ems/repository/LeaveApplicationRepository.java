package org.antwalk.ems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Long> {
	
	@Query("select l from LeaveApplication l where l.employee.empId = :id")
	Page<LeaveApplication> getLeavesById(Long id, Pageable pageable);
	
	@Query("select count(l) from LeaveApplication l where l.employee.empId = :id and l.leaveType='CL' and l.isApproved is null")
	Integer getAppliedCL(Long id);

	@Query("select count(l) from LeaveApplication l where l.employee.empId = :id and l.leaveType='PL' and l.isApproved is null")
	Integer getAppliedPL(Long id);

	@Query("select count(l) from LeaveApplication l where l.employee.empId = :id and l.leaveType='SL' and l.isApproved is null")
	Integer getAppliedSL(Long id);
    
}
