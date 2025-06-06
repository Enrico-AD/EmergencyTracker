package com.emergency.EmergencyTracker.repository;

import com.emergency.EmergencyTracker.model.Sinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinalRepository extends JpaRepository<Sinal, Long> {
    List<Sinal> findByDispositivosIdOrderByDataHoraDesc(Long dispositivoId);
}