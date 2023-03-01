package org.antwalk.ems.controller;

import java.util.List;

import org.antwalk.ems.exception.ResourceNotFoundException;
import org.antwalk.ems.model.Department;
import org.antwalk.ems.repository.DepartmentRepository;
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
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;
    

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody(required = true) Department department){
        departmentRepository.save(department);
        return ResponseEntity.ok().body(department);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Department>> listAllDepartments(){
        return ResponseEntity.ok().body(departmentRepository.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") @Min(1) Long id ) throws ResourceNotFoundException{
        Department department = departmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No department with id " + id + " exists")
        );
        return ResponseEntity.ok().body(department);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") @Min(1) Long id, Department suppliedDepartment ) throws ResourceNotFoundException{
        Department department = departmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No department with id " + id + " exists")
        );
        suppliedDepartment.setDeptId(department.getDeptId());
        departmentRepository.save(suppliedDepartment);
        return ResponseEntity.ok().body(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") @Min(1) Long id, Department suppliedDepartment ) throws ResourceNotFoundException{
        Department department = departmentRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No department with id " + id + " exists")
        );
        departmentRepository.delete(department);
        return ResponseEntity.ok().body(null);
    }
}
