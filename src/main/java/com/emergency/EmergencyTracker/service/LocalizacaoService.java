package com.emergency.EmergencyTracker.service;

import com.emergency.EmergencyTracker.dto.LocalizacaoDTO;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.Localizacao;
import com.emergency.EmergencyTracker.repository.LocalizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Transactional(readOnly = true)
    public List<LocalizacaoDTO> getAllLocalizacoes() {
        return localizacaoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<LocalizacaoDTO> getLocalizacaoById(Long id) {
        return localizacaoRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Transactional
    public LocalizacaoDTO createLocalizacao(LocalizacaoDTO dto) {
        Localizacao localizacao = mapToEntity(dto);
        Localizacao savedLocalizacao = localizacaoRepository.save(localizacao);
        return mapToDTO(savedLocalizacao);
    }

    @Transactional
    public LocalizacaoDTO updateLocalizacao(Long id, LocalizacaoDTO dto) {
        if (localizacaoRepository.existsById(id)) {
            Localizacao localizacaoAtualizada = mapToEntity(dto);
            localizacaoAtualizada.setId(id);
            Localizacao updatedLocalizacao = localizacaoRepository.save(localizacaoAtualizada);
            return mapToDTO(updatedLocalizacao);
        }
        return null;
    }

    @Transactional
    public void deleteLocalizacao(Long id) {
        localizacaoRepository.findById(id).ifPresent(localizacao -> {
            localizacao.setDeleted(true);
            localizacaoRepository.save(localizacao);
        });
    }

    public LocalizacaoDTO mapToDTO(Localizacao localizacao) {
        return LocalizacaoDTO.builder()
                .id(localizacao.getId())
                .latitude(localizacao.getLatitude())
                .longitude(localizacao.getLongitude())
                .descricao(localizacao.getDescricao())
                .dataCriacao(localizacao.getDataCriacao())
                .build();
    }

    public Localizacao mapToEntity(LocalizacaoDTO dto) {
        return Localizacao.builder()
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .descricao(dto.getDescricao())
                .build();
    }
}