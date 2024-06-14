package com.andyg.SpringSecurityApp.persistence.repository;

import com.andyg.SpringSecurityApp.persistence.entity.TestUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestUserRepository extends JpaRepository<TestUsersEntity, Long> {


}
