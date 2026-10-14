package com.sachin.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.entity.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}

