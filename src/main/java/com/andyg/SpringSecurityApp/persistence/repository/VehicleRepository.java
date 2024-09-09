package com.andyg.SpringSecurityApp.persistence.repository;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
