package org.antwalk.ems.repository;

import org.antwalk.ems.model.Resignation;
import org.antwalk.ems.view.EmployeeListView;
import org.antwalk.ems.view.ResignationListAdminView;
import org.antwalk.ems.view.ResignationView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResignationRepository extends JpaRepository<Resignation,Long> {
@Query("from Resignation")
    public Page<Resignation> findAllRecentResignations(Pageable pageable);
    
    @Query(nativeQuery = true, value = "select resignation_reason as resignationReason, resignation_date as resignationDate, is_approved as isApproved, approved_by_admin_id as approvedBy from Resignation where resignation_id = :id")
    public ResignationView findResignationById(Long id);

    @Query(nativeQuery = true, value = "select resignation_id as resignationId, resignation_reason as resignationReason, resignation_date as resignationDate, is_approved as isApproved, employee_emp_id as empId from Resignation" )
    public Page<ResignationListAdminView> findAllResignations(Pageable pageable);
}
