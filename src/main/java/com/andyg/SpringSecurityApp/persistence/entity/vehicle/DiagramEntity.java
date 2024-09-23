package com.andyg.SpringSecurityApp.persistence.entity.vehicle;

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
@Table(name = "diagram")
public class DiagramEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoryEntity category;

    @ManyToMany
    @JoinTable(
            name = "model_diagram",
            joinColumns = @JoinColumn(name = "diagram_id"),
            inverseJoinColumns = @JoinColumn(name = "model_id")
    )
    private Set<ModelEntity> model;



}
