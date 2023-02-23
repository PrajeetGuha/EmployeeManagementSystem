package org.antwalk.ems.controller;

import java.util.List;

import org.antwalk.ems.exception.ResourceNotFoundException;
import org.antwalk.ems.model.User;
import org.antwalk.ems.repository.UserRepository;
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

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody(required = true) User user){
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/listall")
    public ResponseEntity<List<User>> listAllUsers(){
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") @Min(1) Long id ) throws ResourceNotFoundException{
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No user with id " + id + " exists")
        );
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") @Min(1) Long id, User suppliedUser ) throws ResourceNotFoundException{
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No user with id " + id + " exists")
        );
        suppliedUser.setId(user.getId());
        userRepository.save(suppliedUser);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") @Min(1) Long id, User suppliedUser ) throws ResourceNotFoundException{
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("No user with id " + id + " exists")
        );
        userRepository.delete(user);
        return ResponseEntity.ok().body(null);
    }
}
