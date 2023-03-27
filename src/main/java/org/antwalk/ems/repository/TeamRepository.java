package org.antwalk.ems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
	
	@Query("SELECT count(*) from Team GROUP BY department")
	 public List<Integer> teamcount();
	
	@Query("select DISTINCT department FROM Team")
	public List<String> teamdept();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update team set tm_emp_id = null where tm_emp_id = :id")
    void updateTM(Long id);
}
