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


    //lista de usuarios
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ') or hasAuthority('CREATE') or hasAuthority('UPDATE') or hasAuthority('DELETE')")
    public Iterable<UserEntity> userList() {
        return userService.findAll();
    }

    //Busqueda de usuarios por ID
    @GetMapping(value = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('READ') or hasAuthority('CREATE') or hasAuthority('UPDATE') or hasAuthority('DELETE')")
    public UserEntity userGetId(@PathVariable long id) {
        return  userService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {
        user.setDateCreate(LocalDateTime.now());
        return userService.createUser(user);
    }


    @GetMapping("/test")
    @PreAuthorize("hasAuthority('READ') or hasAuthority('CREATE') or hasAuthority('UPDATE') or hasAuthority('DELETE')")
    public String testText() {
        return "Test Text";
    }

}
