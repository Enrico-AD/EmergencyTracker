package com.emergency.EmergencyTracker.repository;

import com.emergency.EmergencyTracker.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    @Query("SELECT a FROM Alerta a JOIN a.usuarios u WHERE u.id = :usuarioId ORDER BY a.dataHora DESC")
    List<Alerta> findByUsuarioIdOrderByDataHoraDesc(@Param("usuarioId") Long usuarioId);

}