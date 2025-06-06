package com.emergency.EmergencyTracker.controller;

import com.emergency.EmergencyTracker.dto.ResponseUsuarioDTO;
import com.emergency.EmergencyTracker.model.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<ResponseUsuarioDTO> login(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() == null) {
            return ResponseEntity.status(401).body(new ResponseUsuarioDTO(null)); // Empty DTO for unauthorized
        }
        Login login = (Login) authentication.getPrincipal();
        return ResponseEntity.ok(new ResponseUsuarioDTO(login.getUsuario()));
    }
}