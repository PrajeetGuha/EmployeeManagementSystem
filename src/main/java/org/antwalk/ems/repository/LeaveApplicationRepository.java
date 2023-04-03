package org.antwalk.ems.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

import org.antwalk.ems.model.LeaveApplication;
import org.antwalk.ems.view.EmployeeListView;
import org.antwalk.ems.view.LeaveApplicationAdminView;
import org.antwalk.ems.view.LeaveApplicationListView;
import org.antwalk.ems.model.LeaveApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication,Long> {
	
	@Query("select l.leaveType as leaveType, l.leaveReason as leaveReason, l.leaveAppliedFor as leaveAppliedFor, l.isApproved as isApproved from LeaveApplication l where l.employee.empId = :id")
	Page<LeaveApplicationListView> getLeavesById(Long id, Pageable pageable);
	
	@Query("select count(l) from LeaveApplication l where l.employee.empId = :id and l.leaveType='CL' and l.isApproved is null")
	Integer getAppliedCL(Long id);

	@Query("select count(l) from LeaveApplication l where l.employee.empId = :id and l.leaveType='PL' and l.isApproved is null")
	Integer getAppliedPL(Long id);

	@Query("select count(l) from LeaveApplication l where l.employee.empId = :id and l.leaveType='SL' and l.isApproved is null")
	Integer getAppliedSL(Long id);

	@Query(nativeQuery = true, value = "select emp_leave_id as empLeaveId, leave_type as leaveType, leave_reason as leaveReason, leave_applied_for as leaveAppliedFor, application_date as applicationDate, is_approved as isApproved, employee_emp_id as empId from leave_application")
    Page<LeaveApplicationAdminView> findAllLeaves(Pageable pageable);
    
}
