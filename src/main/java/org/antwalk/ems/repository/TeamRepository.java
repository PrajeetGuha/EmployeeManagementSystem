package org.antwalk.ems.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.view.TeamDetailsView;
import org.antwalk.ems.view.TeamListView;
import org.antwalk.ems.view.TeamSelectionView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("select t.teamId as teamId, t.teamName as teamName from Team t")
	public List<TeamSelectionView> getAllTeams();
    
    @Query(nativeQuery = true, value = "select team_id as teamId, team_name as teamName, department as department, tm_emp_id as tm from Team")
    public Page<TeamListView> getTeamDetails(Pageable pageable);

    @Query("select t.teamId as teamId, t.teamName as teamName, t.department as department from Team t where t.teamId = :tid")
    public TeamDetailsView findTeamDetails(Long tid);

    @Query("select t.teamName from Team t where t.teamId = :id")
    public String findTeamNameById(Long id);

}
