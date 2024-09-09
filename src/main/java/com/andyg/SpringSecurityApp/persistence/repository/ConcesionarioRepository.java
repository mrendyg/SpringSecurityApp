package com.andyg.SpringSecurityApp.persistence.repository;

import com.andyg.SpringSecurityApp.persistence.entity.user.ConcesionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcesionarioRepository extends JpaRepository<ConcesionarioEntity, Long> {
}
