package com.andyg.SpringSecurityApp.controller;

import com.andyg.SpringSecurityApp.persistence.entity.TestUsersEntity;
import com.andyg.SpringSecurityApp.persistence.repository.TestUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/test")
@PreAuthorize("denyAll()")
public class TestUserController {

    private TestUserRepository testUserRepository;

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public List<TestUsersEntity> getUsers(){
        return testUserRepository.findAll();
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public String postUser(@RequestBody TestUsersEntity testUsers){
        testUserRepository.save(testUsers);
        return "Save User";
    }

}
