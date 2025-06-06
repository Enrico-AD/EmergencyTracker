package com.emergency.EmergencyTracker.controller;

import com.emergency.EmergencyTracker.dto.AlertaDTO;
import com.emergency.EmergencyTracker.service.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/alerta")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping
    public ResponseEntity<List<AlertaDTO>> getAllAlertas() {
        List<AlertaDTO> alertas = alertaService.getAllAlertas();
        return new ResponseEntity<>(alertas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> getAlertaById(@PathVariable Long id) {
        Optional<AlertaDTO> alertaDTO = alertaService.getAlertaById(id);
        return alertaDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<AlertaDTO>> getAlertasPorUsuario(@PathVariable Long id) {
        List<AlertaDTO> alertas = alertaService.buscarAlertasPorUsuarioOrdenado(id);
        return new ResponseEntity<>(alertas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlertaDTO> createAlerta(@RequestBody AlertaDTO dto) {
        AlertaDTO createdAlerta = alertaService.createAlerta(dto);
        return new ResponseEntity<>(createdAlerta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaDTO> updateAlerta(@PathVariable Long id, @RequestBody AlertaDTO dto) {
        AlertaDTO updatedAlerta = alertaService.updateAlerta(id, dto);
        return updatedAlerta != null ?
                new ResponseEntity<>(updatedAlerta, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlerta(@PathVariable Long id) {
        alertaService.deleteAlerta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}