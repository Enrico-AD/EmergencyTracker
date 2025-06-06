package com.emergency.EmergencyTracker.controller;

import com.emergency.EmergencyTracker.dto.TipoLoginDTO;
import com.emergency.EmergencyTracker.service.TipoLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/tipo-login")
@RequiredArgsConstructor
public class TipoLoginController {

    private final TipoLoginService tipoLoginService;

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TipoLoginDTO> createTipoLogin(@Valid @RequestBody TipoLoginDTO tipoLoginDTO) {
        TipoLoginDTO created = tipoLoginService.createTipoLogin(tipoLoginDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<TipoLoginDTO>> getAllTipoLogins() {
        List<TipoLoginDTO> tipoLogins = tipoLoginService.findAll();
        return ResponseEntity.ok(tipoLogins);
    }

 //   @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<TipoLoginDTO> getTipoLoginById(@PathVariable Long id) {
        TipoLoginDTO tipoLogin = tipoLoginService.findById(id);
        return ResponseEntity.ok(tipoLogin);
    }

  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TipoLoginDTO> updateTipoLogin(@PathVariable Long id, @Valid @RequestBody TipoLoginDTO tipoLoginDTO) {
        TipoLoginDTO updated = tipoLoginService.updateTipoLogin(id, tipoLoginDTO);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoLogin(@PathVariable Long id) {
        tipoLoginService.deleteTipoLogin(id);
        return ResponseEntity.noContent().build();
    }
}