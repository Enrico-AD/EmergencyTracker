package com.emergency.EmergencyTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO implements Serializable {

    private boolean success;
    private String message;
    private ResponseUsuarioDTO usuario;
    private String token;
}