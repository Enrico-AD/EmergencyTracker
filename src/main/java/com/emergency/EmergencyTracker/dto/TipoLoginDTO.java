package com.emergency.EmergencyTracker.dto;

import com.emergency.EmergencyTracker.enums.TipoLoginEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoLoginDTO implements Serializable {

    private Long id;
    private TipoLoginEnum descricao;
}