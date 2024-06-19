package com.andyg.SpringSecurityApp.persistence.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private String username;
    private String password;

}
