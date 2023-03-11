package org.antwalk.ems.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.antwalk.ems.dto.NewEmployeeDTO;
import org.antwalk.ems.exception.UserNotFoundException;
import org.antwalk.ems.model.Admin;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.EmployeeDetails;
import org.antwalk.ems.model.User;
import org.antwalk.ems.repository.AdminRepository;
import org.antwalk.ems.repository.EmployeeDetailsRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.view.EmployeeListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public Admin fetchAdminData(Long id) throws UserNotFoundException{
        return Optional.of(adminRepository.getById(id)).orElseThrow(
            () -> new UserNotFoundException("User with id: " + id + " not found")
        );
    }

    public List<EmployeeListView> listEmployees(int pageNo, String search){
        Pageable pageable = PageRequest.of(pageNo-1, PAGE_SIZE, Sort.by("empstatus").and(Sort.by("empName")));
        if (search.equals("null"))
            return employeeRepository.findAllEmployeeListViews(pageable).getContent();
        else
            return employeeRepository.findAllEmployeeListViewsWithSearch(pageable,search).getContent();
    }

    public int countPagesOfEmployees(String search){
        Pageable pageable = PageRequest.of(0, PAGE_SIZE, Sort.by("empstatus").and(Sort.by("empName")));
        if (search.equals("null"))
            return employeeRepository.findAll(pageable).getTotalPages();
        else
            return employeeRepository.findAllBySearch(pageable,search).getTotalPages();
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
    
    public long countEmployees(String search) {
        if (search.equals("null"))
    	    return employeeRepository.count();
        else
            return employeeRepository.countBySearch(search);
    }

    @Transactional
    public void addEmployee(NewEmployeeDTO newEmployeeDTO){

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmailId(newEmployeeDTO.getPersonalEmail());
        employeeDetailsRepository.save(employeeDetails);

        Employee employee = new Employee();
        employee.setEmpName(newEmployeeDTO.getName());
        employee.setWorkEmail(newEmployeeDTO.getWorkEmail());
        employee.setEmployeeDetails(employeeDetails);
        employee.setEmpstatus("ACTIVE");
        employee.setDesignation("UNASSIGNED");
        Employee persistedEmployee = employeeRepository.save(employee);
        
        User user = new User();
        user.setTablePk(persistedEmployee.getEmpId());
        user.setIsEnabled(true);
        user.setPassword(passwordEncoder.encode(newEmployeeDTO.getPassword()));
        user.setRole("ROLE_EMP");
        user.setTablePk(persistedEmployee.getEmpId());
        user.setUsername(newEmployeeDTO.getUsername());
        User persistedUser = userRepository.save(user);
    }
    
}
