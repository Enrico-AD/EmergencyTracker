package com.emergency.EmergencyTracker.dto;

import com.emergency.EmergencyTracker.enums.NivelAlertaEnum;
import com.emergency.EmergencyTracker.enums.StatusAlertaEnum;
import com.emergency.EmergencyTracker.enums.TipoAlertaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlertaDTO implements Serializable {

    private Long id;
    private TipoAlertaEnum tipoAlerta;
    private String conteudo;
    private LocalDateTime dataHora;
    private StatusAlertaEnum status;
    private NivelAlertaEnum nivel;
    private Long idSinal;
    private Long idLocalizacao;
    private List<Long> usuarioIds; // Lista de IDs de usuários associados
    private LocalDateTime dataCriacao;
}