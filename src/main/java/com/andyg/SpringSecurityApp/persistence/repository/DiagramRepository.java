package com.andyg.SpringSecurityApp.persistence.repository;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.DiagramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagramRepository extends JpaRepository<DiagramEntity, Long> {
}
