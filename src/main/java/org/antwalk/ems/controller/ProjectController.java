package org.antwalk.ems.controller;

import java.util.List;

import org.antwalk.ems.exception.ResourceNotFoundException;
import org.antwalk.ems.model.Project;
import org.antwalk.ems.repository.ProjectRepository;
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
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;
    

    @PostMapping("/add")
    public ResponseEntity<Project> addProject(@RequestBody(required = true) Project project){
        projectRepository.save(project);
        return ResponseEntity.ok().body(project);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Project>> listAllProjects(){
        return ResponseEntity.ok().body(projectRepository.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Project> getById(@PathVariable("id") @Min(1) Long id ) throws ResourceNotFoundException{
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No project with id " + id + " exists")
        );
        return ResponseEntity.ok().body(project);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") @Min(1) Long id, Project suppliedProject ) throws ResourceNotFoundException{
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No project with id " + id + " exists")
        );
        suppliedProject.setProjId(project.getProjId());
        projectRepository.save(suppliedProject);
        return ResponseEntity.ok().body(project);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("id") @Min(1) Long id, Project suppliedProject ) throws ResourceNotFoundException{
        Project project = projectRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No project with id " + id + " exists")
        );
        projectRepository.delete(project);
        return ResponseEntity.ok().body(null);
    }
}
