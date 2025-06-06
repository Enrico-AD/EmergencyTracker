package com.emergency.EmergencyTracker.service;

import com.emergency.EmergencyTracker.dto.SolicitacaoAjudaDTO;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.SolicitacaoAjuda;
import com.emergency.EmergencyTracker.model.Usuario;
import com.emergency.EmergencyTracker.repository.SolicitacaoAjudaRepository;
import com.emergency.EmergencyTracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitacaoAjudaService {

    @Autowired
    private SolicitacaoAjudaRepository solicitacaoAjudaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<SolicitacaoAjudaDTO> getAllSolicitacoes() {
        return solicitacaoAjudaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<SolicitacaoAjudaDTO> getSolicitacaoById(Long id) {
        return solicitacaoAjudaRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Transactional(readOnly = true)
    public List<SolicitacaoAjudaDTO> buscarSolicitacoesPorUsuario(Long usuarioId) {
        return solicitacaoAjudaRepository.findByUsuarioIdOrderByDataHoraDesc(usuarioId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SolicitacaoAjudaDTO createSolicitacao(SolicitacaoAjudaDTO dto) {
        SolicitacaoAjuda solicitacao = mapToEntity(dto);
        SolicitacaoAjuda savedSolicitacao = solicitacaoAjudaRepository.save(solicitacao);
        return mapToDTO(savedSolicitacao);
    }

    @Transactional
    public SolicitacaoAjudaDTO updateSolicitacao(Long id, SolicitacaoAjudaDTO dto) {
        if (solicitacaoAjudaRepository.existsById(id)) {
            SolicitacaoAjuda solicitacaoAtualizada = mapToEntity(dto);
            solicitacaoAtualizada.setId(id);
            SolicitacaoAjuda updatedSolicitacao = solicitacaoAjudaRepository.save(solicitacaoAtualizada);
            return mapToDTO(updatedSolicitacao);
        }
        return null;
    }

    @Transactional
    public void deleteSolicitacao(Long id) {
        solicitacaoAjudaRepository.findById(id).ifPresent(solicitacao -> {
            solicitacao.setDeleted(true);
            solicitacaoAjudaRepository.save(solicitacao);
        });
    }

    public SolicitacaoAjudaDTO mapToDTO(SolicitacaoAjuda solicitacao) {
        return SolicitacaoAjudaDTO.builder()
                .id(solicitacao.getId())
                .tipoSolicitacao(solicitacao.getTipoSolicitacao())
                .conteudo(solicitacao.getConteudo())
                .dataHora(solicitacao.getDataHora())
                .status(solicitacao.getStatus())
                .nivel(solicitacao.getNivel())
                .usuarioId(solicitacao.getUsuario().getId())
                .dataCriacao(solicitacao.getDataCriacao())
                .build();
    }

    public SolicitacaoAjuda mapToEntity(SolicitacaoAjudaDTO dto) {
        SolicitacaoAjuda solicitacao = new SolicitacaoAjuda();
        solicitacao.setTipoSolicitacao(dto.getTipoSolicitacao());
        solicitacao.setConteudo(dto.getConteudo());
        solicitacao.setDataHora(dto.getDataHora());
        solicitacao.setStatus(dto.getStatus());
        solicitacao.setNivel(dto.getNivel());
        solicitacao.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com ID: " + dto.getUsuarioId())));
        return solicitacao;
    }
}