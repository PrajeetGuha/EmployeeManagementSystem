package org.antwalk.ems.controller;

import java.util.List;
import java.util.Optional;

import org.antwalk.ems.model.Employee;
import org.antwalk.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    

    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }

    @GetMapping("/listall")
    public List<Employee> listAllEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<Employee> getById(@PathVariable("id") Long id ){
        return employeeRepository.findById(id);
    }
}
