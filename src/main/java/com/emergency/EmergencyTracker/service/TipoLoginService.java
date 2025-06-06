package com.emergency.EmergencyTracker.service;

import com.emergency.EmergencyTracker.dto.TipoLoginDTO;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.TipoLogin;
import com.emergency.EmergencyTracker.repository.TipoLoginRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoLoginService {

    @Autowired
    private TipoLoginRepository tipoLoginRepository;

    @Transactional
    public TipoLoginDTO createTipoLogin(TipoLoginDTO dto) {
        TipoLogin tipoLogin = mapToEntity(dto);
        TipoLogin saved = tipoLoginRepository.save(tipoLogin);
        return mapToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<TipoLoginDTO> getAllTipoLogins() {
        return tipoLoginRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TipoLoginDTO> findAll() {
        return tipoLoginRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TipoLoginDTO findById(Long id) {
        return tipoLoginRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ObjectNotFoundException("Tipo de login não encontrado com ID: " + id));
    }

    @Transactional
    public TipoLoginDTO updateTipoLogin(Long id, @Valid TipoLoginDTO dto) {
        return tipoLoginRepository.findById(id)
                .map(existing -> {
                    TipoLogin tipoLogin = mapToEntity(dto);
                    tipoLogin.setId(id);
                    TipoLogin updated = tipoLoginRepository.save(tipoLogin);
                    return mapToDTO(updated);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Tipo de login não encontrado com ID: " + id));
    }

    @Transactional
    public void deleteTipoLogin(Long id) {
        if (!tipoLoginRepository.existsById(id)) {
            throw new ObjectNotFoundException("Tipo de login não encontrado com ID: " + id);
        }
        tipoLoginRepository.deleteById(id);
    }

    private TipoLoginDTO mapToDTO(TipoLogin tipoLogin) {
        return TipoLoginDTO.builder()
                .id(tipoLogin.getId())
                .descricao(tipoLogin.getDescricao())
                .build();
    }

    private TipoLogin mapToEntity(TipoLoginDTO dto) {
        return TipoLogin.builder()
                .descricao(dto.getDescricao())
                .build();
    }
}