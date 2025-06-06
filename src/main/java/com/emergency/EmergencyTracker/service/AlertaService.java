package com.emergency.EmergencyTracker.service;

import com.emergency.EmergencyTracker.dto.AlertaDTO;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.Alerta;
import com.emergency.EmergencyTracker.model.Localizacao;
import com.emergency.EmergencyTracker.model.Sinal;
import com.emergency.EmergencyTracker.model.Usuario;
import com.emergency.EmergencyTracker.repository.AlertaRepository;
import com.emergency.EmergencyTracker.repository.LocalizacaoRepository;
import com.emergency.EmergencyTracker.repository.SinalRepository;
import com.emergency.EmergencyTracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private SinalRepository sinalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Transactional
    public List<AlertaDTO> buscarAlertasPorUsuarioOrdenado(Long usuarioId) {
        List<AlertaDTO> alertas = alertaRepository.findByUsuarioIdOrderByDataHoraDesc(usuarioId).stream()
                .map(this::mapToDTO)
                .toList();
        if (alertas.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum alerta encontrado para o usuário com ID: " + usuarioId);
        }
        return alertas;
    }

    @Transactional
    public List<AlertaDTO> getAllAlertas() {
        return alertaRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional
    public Optional<AlertaDTO> getAlertaById(Long id) {
        return alertaRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Transactional
    public AlertaDTO createAlerta(AlertaDTO dto) {
        Alerta alerta = mapToEntity(dto);
        // Associar usuários
        List<Usuario> usuarios = new ArrayList<>();
        if (dto.getUsuarioIds() != null && !dto.getUsuarioIds().isEmpty()) {
            for (Long usuarioId : dto.getUsuarioIds()) {
                Usuario usuario = usuarioRepository.findById(usuarioId)
                        .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com ID: " + usuarioId));
                usuarios.add(usuario);
            }
        }
        alerta.setUsuarios(usuarios);

        Alerta savedAlerta = alertaRepository.save(alerta);
        return mapToDTO(savedAlerta);
    }

    @Transactional
    public AlertaDTO updateAlerta(Long id, AlertaDTO dto) {
        if (alertaRepository.existsById(id)) {
            Alerta alertaAtualizado = mapToEntity(dto);
            alertaAtualizado.setId(id);
            // Atualizar usuários
            List<Usuario> usuarios = new ArrayList<>();
            if (dto.getUsuarioIds() != null) {
                for (Long usuarioId : dto.getUsuarioIds()) {
                    Usuario usuario = usuarioRepository.findById(usuarioId)
                            .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com ID: " + usuarioId));
                    usuarios.add(usuario);
                }
            }
            alertaAtualizado.setUsuarios(usuarios);

            Alerta updatedAlerta = alertaRepository.save(alertaAtualizado);
            return mapToDTO(updatedAlerta);
        }
        return null;
    }

    @Transactional
    public void deleteAlerta(Long id) {
        alertaRepository.findById(id).ifPresent(alerta -> {
            alerta.setDeleted(true);
            alertaRepository.save(alerta);
        });
    }

    public AlertaDTO mapToDTO(Alerta alerta) {
        return AlertaDTO.builder()
                .id(alerta.getId())
                .tipoAlerta(alerta.getTipoAlerta())
                .conteudo(alerta.getConteudo())
                .dataHora(alerta.getDataHora())
                .status(alerta.getStatus())
                .nivel(alerta.getNivel())
                .idSinal(alerta.getSinal() != null ? alerta.getSinal().getId() : null)
                .idLocalizacao(alerta.getLocalizacao() != null ? alerta.getLocalizacao().getId() : null)
                .usuarioIds(alerta.getUsuarios() != null ?
                        alerta.getUsuarios().stream().map(Usuario::getId).collect(Collectors.toList()) : null)
                .dataCriacao(alerta.getDataCriacao())
                .build();
    }

    public Alerta mapToEntity(AlertaDTO dto) {
        Alerta alerta = new Alerta();
        alerta.setTipoAlerta(dto.getTipoAlerta());
        alerta.setConteudo(dto.getConteudo());
        alerta.setDataHora(dto.getDataHora());
        alerta.setStatus(dto.getStatus());
        alerta.setNivel(dto.getNivel());
        if (dto.getIdSinal() != null) {
            Sinal sinal = sinalRepository.findById(dto.getIdSinal())
                    .orElseThrow(() -> new ObjectNotFoundException("Sinal não encontrado com ID: " + dto.getIdSinal()));
            alerta.setSinal(sinal);
        }
        Localizacao localizacao = localizacaoRepository.findById(dto.getIdLocalizacao())
                .orElseThrow(() -> new ObjectNotFoundException("Localização não encontrada com ID: " + dto.getIdLocalizacao()));
        alerta.setLocalizacao(localizacao);
        return alerta;
    }
}