package com.andyg.SpringSecurityApp.service;

import com.andyg.SpringSecurityApp.persistence.entity.UserEntity;
import com.andyg.SpringSecurityApp.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Iterable<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById (long id){
        return  userRepository.findById(id).orElse(null);
    }

    public UserEntity createUser(UserEntity user){
        user.setDateCreate(LocalDateTime.now());
        return userRepository.save(user);
    }

}
