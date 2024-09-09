package com.andyg.SpringSecurityApp.persistence.entity.user;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.MarcaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "concesionario")
public class ConcesionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "Concesionario_marca",
            joinColumns = @JoinColumn(name = "marca_id"),
            inverseJoinColumns = @JoinColumn(name = "concesionario_id")
    )
    private Set<MarcaEntity> marca;

}
