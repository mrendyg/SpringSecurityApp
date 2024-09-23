package com.andyg.SpringSecurityApp.persistence.entity.vehicle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    private String description;
    private String image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "item_diagram",
            joinColumns = @JoinColumn(name = "item_id"),  // Cambiado a 'item_id'
            inverseJoinColumns = @JoinColumn(name = "diagram_id")
    )
    private Set<DiagramEntity> diagrams;

//    @ManyToMany
//    @JoinTable(
//            name = "item_model",
//            joinColumns = @JoinColumn(name = "marca_id"),
//            inverseJoinColumns = @JoinColumn(name = "model_id")
//    )
//    private Set<ModelEntity> models;

}
