package com.emergency.EmergencyTracker.dto;

import com.emergency.EmergencyTracker.enums.ProcessamentoEnum;
import com.emergency.EmergencyTracker.enums.TipoSinalEnum;
import com.emergency.EmergencyTracker.enums.UnidadeSinalEnum;
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
public class SinalDTO implements Serializable {

    private Long id;
    private TipoSinalEnum tipoSinal;
    private Double valor;
   // private UnidadeSinalEnum unidade;
    private String unidade;
    private LocalDateTime dataHora;
    private ProcessamentoEnum processamento;
    private Long idLocalizacao;
    private List<Long> dispositivoIds;
    private LocalDateTime dataCriacao;
}