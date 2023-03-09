package org.antwalk.ems.service;

import java.util.List;
import java.util.Optional;

import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;
import org.antwalk.ems.repository.AdminRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.view.EmployeeListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.antwalk.ems.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository UserRepository;

    @Value("${employees.fetch.pagesize}")
    private int PAGE_SIZE;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    public Admin fetchAdminData(Long id) throws UserNotFoundException{
        return Optional.of(adminRepository.getById(id)).orElseThrow(
            () -> new UserNotFoundException("User with id: " + id + " not found")
        );
    }

    public List<EmployeeListView> listEmployees(int pageNo){
        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("empId"));
        return employeeRepository.findAllEmployeeListViews(pageable).getContent();
    }

    public int countPagesOfEmployees(){
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("empId"));
        return employeeRepository.findAll(pageable).getTotalPages();
    }

    public void deactivateEmp(Long empId) throws UserNotFoundException{
        if (employeeRepository.existsById(empId)){
            employeeRepository.deactivateEmpById(empId);
            userRepository.disableUserById(empId);
        }
        else{
            throw new UserNotFoundException("User with id: " + empId + " not found");
        }
    }

    public void activateEmp(Long empId) throws UserNotFoundException{
        if (employeeRepository.existsById(empId)){
            employeeRepository.activateEmpById(empId);
            userRepository.enableUserById(empId);
        }
        else{
            throw new UserNotFoundException("User with id: " + empId + " not found");
        }
    }
    
    public long countEmployees() {
    	return employeeRepository.count();
    }
    
}
