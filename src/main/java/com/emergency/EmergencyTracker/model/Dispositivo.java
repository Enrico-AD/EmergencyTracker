package com.emergency.EmergencyTracker.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


import com.emergency.EmergencyTracker.enums.StatusDispositivoEnum;
import com.emergency.EmergencyTracker.enums.TipoDispositivoEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Dispositivo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDispositivoEnum tipoDispositivo;
    @Enumerated(EnumType.STRING)
    private StatusDispositivoEnum status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    @CreationTimestamp
    @Column(name = "dh_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    @UpdateTimestamp
    @Column(name = "dh_update", updatable = false)
    private LocalDateTime dataAtualizacao;
    @Column(nullable = false)
    private boolean deleted = false;
}