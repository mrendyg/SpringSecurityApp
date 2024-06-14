package com.andyg.SpringSecurityApp.controller;

import com.andyg.SpringSecurityApp.persistence.entity.UserEntity;
import com.andyg.SpringSecurityApp.persistence.repository.UserRepository;
import com.andyg.SpringSecurityApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;


@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/auth/users")
@PreAuthorize("denyAll()")
public class UsersController {

    private UserService userService;
    private UserRepository userRepository;


    //lista de usuarios
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ') or hasAuthority('DELETE')")
    public Iterable<UserEntity> userList() {
        return userService.findAll();
    }

    //Busqueda de usuarios por ID
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ') or hasAuthority('UPDATE')")
    public UserEntity userGetId(@PathVariable long id) {
        return  userService.findById(id);
    }

    //Creacion de usuarios
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public UserEntity createUser(@RequestBody UserEntity user) {
        user.setDateCreate(LocalDateTime.now());
        return userService.createdUser(user);
    }

    //Get de pruebas de acceso
    @GetMapping("/test")
    @PreAuthorize("hasRole('DEVELOPER')")
    public String testText() {
        return "Test Text";
    }

}
