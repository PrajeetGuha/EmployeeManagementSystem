package org.antwalk.ems.service;

import org.antwalk.ems.model.Admin;
import org.antwalk.ems.model.Employee;
import org.antwalk.ems.model.User;
import org.antwalk.ems.repository.AdminRepository;
import org.antwalk.ems.repository.EmployeeRepository;
import org.antwalk.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AdminRepository adminRepository;
    
    public User createUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        if (user.getRole().equals("ROLE_USER")){
            Employee employee = new Employee();
            employee.setEmpId(savedUser.getUserId());
            employeeRepository.save(employee);
        }
        else{
            Admin admin = new Admin();
            admin.setAdminId(savedUser.getUserId());
            adminRepository.save(admin);
        }
        return savedUser;
    }
}
