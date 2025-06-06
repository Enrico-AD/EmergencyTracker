package com.emergency.EmergencyTracker.controller;

import com.emergency.EmergencyTracker.dto.LocalizacaoDTO;
import com.emergency.EmergencyTracker.service.LocalizacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/localizacao")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public ResponseEntity<List<LocalizacaoDTO>> getAllLocalizacoes() {
        List<LocalizacaoDTO> localizacoes = localizacaoService.getAllLocalizacoes();
        return new ResponseEntity<>(localizacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> getLocalizacaoById(@PathVariable Long id) {
        Optional<LocalizacaoDTO> localizacaoDTO = localizacaoService.getLocalizacaoById(id);
        return localizacaoDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<LocalizacaoDTO> createLocalizacao(@RequestBody LocalizacaoDTO dto) {
        LocalizacaoDTO createdLocalizacao = localizacaoService.createLocalizacao(dto);
        return new ResponseEntity<>(createdLocalizacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalizacaoDTO> updateLocalizacao(@PathVariable Long id, @RequestBody LocalizacaoDTO dto) {
        LocalizacaoDTO updatedLocalizacao = localizacaoService.updateLocalizacao(id, dto);
        return updatedLocalizacao != null ?
                new ResponseEntity<>(updatedLocalizacao, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalizacao(@PathVariable Long id) {
        localizacaoService.deleteLocalizacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}