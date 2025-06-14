package com.emergency.EmergencyTracker.repository;

import com.emergency.EmergencyTracker.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByLogin(String login);
}