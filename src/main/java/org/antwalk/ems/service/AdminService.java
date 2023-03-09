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

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Value("${employees.fetch.pagesize}")
    private int PAGE_SIZE;

    @Autowired
    EmployeeRepository employeeRepository;

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
    
    public long countEmployees() {
    	return employeeRepository.count();
    }
    
}
