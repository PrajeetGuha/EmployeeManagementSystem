package org.antwalk.ems.controller;

import java.util.List;

import org.antwalk.ems.exception.ResourceNotFoundException;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody(required = true) Employee employee){
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<Employee>> listAllEmployees(){
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") @Min(1) Long id ) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No employee with id " + id + " exists")
        );
        return ResponseEntity.ok().body(employee);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") @Min(1) Long id, @RequestBody(required = true)Employee suppliedEmployee ) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No employee with id " + id + " exists")
        );
        suppliedEmployee.setEmpId(employee.getEmpId());
        employeeRepository.save(suppliedEmployee);
        return ResponseEntity.ok().body(employee);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") @Min(1) Long id, Employee suppliedEmployee ) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No employee with id " + id + " exists")
        );
        employeeRepository.delete(employee);
        return ResponseEntity.ok().body(null);
    }
}
