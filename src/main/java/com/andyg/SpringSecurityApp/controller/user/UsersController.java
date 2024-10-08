package com.andyg.SpringSecurityApp.controller.user;

import com.andyg.SpringSecurityApp.persistence.DTO.UserDTO;
import com.andyg.SpringSecurityApp.persistence.entity.user.UserEntity;
import com.andyg.SpringSecurityApp.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public Iterable<UserEntity> userList() {
        return userService.findAll();
    }

    //Busqueda de usuarios por ID
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or hasRole('DEVELOPER')")
    public UserEntity userGetId(@PathVariable long id) {
        return  userService.findById(id);
    }

    //Creacion de usuarios
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public UserEntity createUser(@RequestBody UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return userService.createsUser(user, userDTO.getPassword());
    }

    //Actualization de usuarios
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('DEVELOPER') or hasRole('ADMIN')")
    public UserEntity updateUser(@PathVariable long id, @RequestBody UserEntity user){
        return  userService.updatesUser(id, user);
    }

    //Borrado de usuarios
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('DEVELOPER')")
    public void deleteUser(@PathVariable long id){
        userService.deletesUser(id);
    }

}
