package org.antwalk.ems.repository;

import java.util.List;

import org.antwalk.ems.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 * public interface Team_ProjectRepository extends JpaRepository<Long,Long>{
 * 
 * @Query("SELECT COUNT(*) as teamcount FROM team_project  GROUP BY proj_id")
 * List<Integer> findteamcount(); }
 */
