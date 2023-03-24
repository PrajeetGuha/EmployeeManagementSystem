package org.antwalk.ems.repository;

import java.util.List;

import org.antwalk.ems.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
	
//	@Query("SELECT count(*) from Team GROUP BY department")
//	 public List<Integer> teamcount();
}
