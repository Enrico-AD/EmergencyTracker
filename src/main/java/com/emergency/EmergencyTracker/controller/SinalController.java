package com.emergency.EmergencyTracker.controller;

import com.emergency.EmergencyTracker.dto.SinalDTO;
import com.emergency.EmergencyTracker.service.SinalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/sinal")
public class SinalController {

    @Autowired
    private SinalService sinalService;

    @GetMapping
    public ResponseEntity<List<SinalDTO>> getAllSinais() {
        List<SinalDTO> sinais = sinalService.getAllSinais();
        return new ResponseEntity<>(sinais, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SinalDTO> getSinalById(@PathVariable Long id) {
        Optional<SinalDTO> sinalDTO = sinalService.getSinalById(id);
        return sinalDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/dispositivo/{dispositivoId}")
    public ResponseEntity<List<SinalDTO>> getSinaisPorDispositivo(@PathVariable Long dispositivoId) {
        List<SinalDTO> sinais = sinalService.getSinaisPorDispositivo(dispositivoId);
        return new ResponseEntity<>(sinais, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SinalDTO> createSinal(@RequestBody SinalDTO dto) {
        SinalDTO createdSinal = sinalService.createSinal(dto);
        return new ResponseEntity<>(createdSinal, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SinalDTO> updateSinal(@PathVariable Long id, @RequestBody SinalDTO dto) {
        SinalDTO updatedSinal = sinalService.updateSinal(id, dto);
        return updatedSinal != null ?
                new ResponseEntity<>(updatedSinal, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinal(@PathVariable Long id) {
        sinalService.deleteSinal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}