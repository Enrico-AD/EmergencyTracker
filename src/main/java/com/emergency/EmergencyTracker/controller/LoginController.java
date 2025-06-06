package com.emergency.EmergencyTracker.controller;

import com.emergency.EmergencyTracker.dto.LoginDTO;
import com.emergency.EmergencyTracker.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{usuarioId}/{tipoLoginId}")
    public ResponseEntity<LoginDTO> createLogin(
            @Valid @RequestBody LoginDTO loginDTO,
            @PathVariable Long usuarioId,
            @PathVariable Long tipoLoginId) {
        LoginDTO created = loginService.createLogin(loginDTO, usuarioId, tipoLoginId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

 //   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<LoginDTO>> getAllLogins() {
        List<LoginDTO> logins = loginService.findAll();
        return ResponseEntity.ok(logins);
    }

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LoginDTO> getLoginById(@PathVariable Long id) {
        LoginDTO login = loginService.findById(id);
        return ResponseEntity.ok(login);
    }

   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}/{tipoLoginId}")
    public ResponseEntity<LoginDTO> updateLogin(
            @PathVariable Long id,
            @PathVariable Long tipoLoginId,
            @Valid @RequestBody LoginDTO loginDTO) {
        LoginDTO updated = loginService.updateLogin(id, tipoLoginId, loginDTO);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) {
        loginService.deleteLogin(id);
        return ResponseEntity.noContent().build();
    }
}