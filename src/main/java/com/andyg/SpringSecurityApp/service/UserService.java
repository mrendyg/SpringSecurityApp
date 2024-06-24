package com.andyg.SpringSecurityApp.service;

import com.andyg.SpringSecurityApp.persistence.entity.UserEntity;
import com.andyg.SpringSecurityApp.persistence.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById (long id){
        return  userRepository.findById(id).orElse(null);
    }

    public UserEntity createsUser(UserEntity user, String rawPassword) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setDateCreate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public UserEntity updatesUser (long id, UserEntity user){
        UserEntity updatedUser = findById(id);
        updatedUser.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            updatedUser.setPassword(encodedPassword);
        }
        updatedUser.setEnable(user.isEnable());
        updatedUser.setAccountNoExpired(user.isAccountNoExpired());
        updatedUser.setAccountNoLocked(user.isAccountNoLocked());
        updatedUser.setCredentialNoExpired(user.isCredentialNoExpired());
        updatedUser.setRoles(user.getRoles());
        return userRepository.save(updatedUser);
    }

    public void deletesUser(long id){
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            UserEntity deletedUser = optionalUser.get();
            deletedUser.getRoles().clear();
            userRepository.delete(deletedUser);
        } else {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
    }

}
