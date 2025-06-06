package com.emergency.EmergencyTracker.dto;

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
public class LocalizacaoDTO implements Serializable {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String descricao;
    private LocalDateTime dataCriacao;
}