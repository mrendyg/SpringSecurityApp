package com.andyg.SpringSecurityApp.persistence.repository;

import com.andyg.SpringSecurityApp.persistence.entity.vehicle.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
