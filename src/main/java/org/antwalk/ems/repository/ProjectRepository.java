package org.antwalk.ems.repository;


import java.util.List;

import org.antwalk.ems.model.Project;
import org.antwalk.ems.view.ProjectListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    
	
//	   @Query ("SELECT p.projectName FROM team_project tp JOIN Project p ON tp.proj_id = p.projId GROUP BY tp.proj_id")
//	   List<String> teamsinprojects();
//	   
	@Query("SELECT COUNT(t) as teamcount FROM Project p JOIN p.teams t GROUP BY p.id")
	List<Integer> findTeamCountByProject();

	@Query("SELECT p.projectName FROM Project p JOIN p.teams t GROUP BY p.id HAVING COUNT(t) > 0")
	List<String> findProjectsWithTeams();
	
	@Query(nativeQuery = true, value = "select proj_id as projId, project_name as projectName, start_date as startDate, end_date as endDate, pm_emp_id as pm from Project ")
	Page<ProjectListView> getProjectDetails(Pageable pageable);

}
