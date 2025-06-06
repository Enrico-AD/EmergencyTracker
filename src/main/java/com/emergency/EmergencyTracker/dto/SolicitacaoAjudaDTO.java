package com.emergency.EmergencyTracker.dto;

import com.emergency.EmergencyTracker.enums.NivelSolicitacaoAjudaEnum;
import com.emergency.EmergencyTracker.enums.StatusSolicitacaoAjudaEnum;
import com.emergency.EmergencyTracker.enums.TipoSolicitacaoAjudaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitacaoAjudaDTO {
    private Long id;
    private TipoSolicitacaoAjudaEnum tipoSolicitacao;
    private String conteudo;
    private LocalDateTime dataHora;
    private StatusSolicitacaoAjudaEnum status;
    private NivelSolicitacaoAjudaEnum nivel;
    private Long usuarioId;
    private LocalDateTime dataCriacao;
}