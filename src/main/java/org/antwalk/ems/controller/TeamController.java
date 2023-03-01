package org.antwalk.ems.controller;

import java.util.List;

import org.antwalk.ems.exception.ResourceNotFoundException;
import org.antwalk.ems.model.Team;
import org.antwalk.ems.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamRepository teamRepository;
    

    @PostMapping("/add")
    public ResponseEntity<Team> addTeam(@RequestBody(required = true) Team team){
        teamRepository.save(team);
        return ResponseEntity.ok().body(team);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Team>> listAllTeams(){
        return ResponseEntity.ok().body(teamRepository.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Team> getById(@PathVariable("id") @Min(1) Long id ) throws ResourceNotFoundException{
        Team team = teamRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No team with id " + id + " exists")
        );
        return ResponseEntity.ok().body(team);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable("id") @Min(1) Long id, Team suppliedTeam ) throws ResourceNotFoundException{
        Team team = teamRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No team with id " + id + " exists")
        );
        suppliedTeam.setTeamId(team.getTeamId());
        teamRepository.save(suppliedTeam);
        return ResponseEntity.ok().body(team);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") @Min(1) Long id, Team suppliedTeam ) throws ResourceNotFoundException{
        Team team = teamRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No team with id " + id + " exists")
        );
        teamRepository.delete(team);
        return ResponseEntity.ok().body(null);
    }
}
