package com.emergency.EmergencyTracker.dto;

import com.emergency.EmergencyTracker.enums.StatusDispositivoEnum;
import com.emergency.EmergencyTracker.enums.TipoDispositivoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DispositivoDTO implements Serializable {

    private Long id;
    private TipoDispositivoEnum tipoDispositivo;
    private StatusDispositivoEnum status;
    private Long usuarioId;
    private Double latitude;
    private Double longitude;
    private LocalDateTime dataCriacao;
}
