package com.emergency.EmergencyTracker.repository;

import com.emergency.EmergencyTracker.model.TipoLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLoginRepository extends JpaRepository<TipoLogin, Long> {

}