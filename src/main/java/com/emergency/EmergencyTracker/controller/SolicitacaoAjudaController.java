package com.emergency.EmergencyTracker.controller;

import com.emergency.EmergencyTracker.dto.SolicitacaoAjudaDTO;
import com.emergency.EmergencyTracker.service.SolicitacaoAjudaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/solicitacao-ajuda")
public class SolicitacaoAjudaController {

    @Autowired
    private SolicitacaoAjudaService solicitacaoAjudaService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoAjudaDTO>> getAllSolicitacoes() {
        List<SolicitacaoAjudaDTO> solicitacoes = solicitacaoAjudaService.getAllSolicitacoes();
        return new ResponseEntity<>(solicitacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoAjudaDTO> getSolicitacaoById(@PathVariable Long id) {
        Optional<SolicitacaoAjudaDTO> solicitacaoDTO = solicitacaoAjudaService.getSolicitacaoById(id);
        return solicitacaoDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<SolicitacaoAjudaDTO>> getSolicitacoesPorUsuario(@PathVariable Long usuarioId) {
        List<SolicitacaoAjudaDTO> solicitacoes = solicitacaoAjudaService.buscarSolicitacoesPorUsuario(usuarioId);
        return new ResponseEntity<>(solicitacoes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoAjudaDTO> createSolicitacao(@RequestBody SolicitacaoAjudaDTO dto) {
        SolicitacaoAjudaDTO createdSolicitacao = solicitacaoAjudaService.createSolicitacao(dto);
        return new ResponseEntity<>(createdSolicitacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoAjudaDTO> updateSolicitacao(@PathVariable Long id, @RequestBody SolicitacaoAjudaDTO dto) {
        SolicitacaoAjudaDTO updatedSolicitacao = solicitacaoAjudaService.updateSolicitacao(id, dto);
        return updatedSolicitacao != null ?
                new ResponseEntity<>(updatedSolicitacao, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable Long id) {
        solicitacaoAjudaService.deleteSolicitacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}