package com.emergency.EmergencyTracker.repository;

import com.emergency.EmergencyTracker.model.SolicitacaoAjuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoAjudaRepository extends JpaRepository<SolicitacaoAjuda, Long> {
    List<SolicitacaoAjuda> findByUsuarioIdOrderByDataHoraDesc(Long usuarioId);
}