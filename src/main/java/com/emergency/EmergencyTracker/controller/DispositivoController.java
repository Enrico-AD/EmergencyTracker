package com.emergency.EmergencyTracker.controller;

import java.util.List;
import java.util.Optional;

import com.emergency.EmergencyTracker.dto.DispositivoDTO;
import com.emergency.EmergencyTracker.service.DispositivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "rest/dispositivo")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    @GetMapping
    public List<DispositivoDTO> getAllDispositivos() {
        return dispositivoService.getAllDispositivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoDTO> getDispositivoById(@PathVariable Long id) {
        Optional<DispositivoDTO> dispositivoDTO = dispositivoService.getDispositivoById(id);
        return dispositivoDTO.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DispositivoDTO> createDispositivo(@RequestBody DispositivoDTO dto) {
        DispositivoDTO createdDispositivo = dispositivoService.createDispositivo(dto);
        return new ResponseEntity<>(createdDispositivo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoDTO> updateDispositivo(@PathVariable Long id, @RequestBody DispositivoDTO dto) {
        DispositivoDTO updatedDispositivo = dispositivoService.updateDispositivo(id, dto);
        return updatedDispositivo != null ?
                new ResponseEntity<>(updatedDispositivo, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispositivo(@PathVariable Long id) {
        dispositivoService.deleteDispositivo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}