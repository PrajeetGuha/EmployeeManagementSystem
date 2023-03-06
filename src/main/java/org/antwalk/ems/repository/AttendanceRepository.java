package org.antwalk.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.antwalk.ems.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    
}
