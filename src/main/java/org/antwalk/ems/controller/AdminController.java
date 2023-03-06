package org.antwalk.ems.controller;

import javax.validation.Valid;

import org.antwalk.ems.model.User;
import org.antwalk.ems.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/admin")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminController {

    @Autowired
    UserService userService;
    
    @PostMapping("/addUser")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createUser(user));
    }
}
