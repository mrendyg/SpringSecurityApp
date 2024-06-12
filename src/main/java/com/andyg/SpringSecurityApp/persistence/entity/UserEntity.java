package com.andyg.SpringSecurityApp.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;  //Permite una lista sin repeticion de datos

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(name = "is_enable")
    private boolean isEnable; //Esta habilitado

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired; //la cuenta no ha expirado

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked; //la cuenta no esta bloqueada

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired; //las credenciales no han expirado

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles= new HashSet<>();
    //tabla de unificacion de Usuario con sus Roles

    @Column(name = "DataTime_Create")
    private LocalDateTime DateCreate;

}

