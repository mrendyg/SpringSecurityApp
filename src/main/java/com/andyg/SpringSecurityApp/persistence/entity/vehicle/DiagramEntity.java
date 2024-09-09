package com.andyg.SpringSecurityApp.persistence.entity.vehicle;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelEntity model;

}
