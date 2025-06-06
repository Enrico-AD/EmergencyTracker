package com.emergency.EmergencyTracker.service;

import com.emergency.EmergencyTracker.dto.SinalDTO;
import com.emergency.EmergencyTracker.enums.UnidadeSinalEnum;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.Dispositivo;
import com.emergency.EmergencyTracker.model.Localizacao;
import com.emergency.EmergencyTracker.model.Sinal;
import com.emergency.EmergencyTracker.repository.DispositivoRepository;
import com.emergency.EmergencyTracker.repository.LocalizacaoRepository;
import com.emergency.EmergencyTracker.repository.SinalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SinalService {

    @Autowired
    private SinalRepository sinalRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    @Transactional(readOnly = true)
    public List<SinalDTO> getAllSinais() {
        return sinalRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<SinalDTO> getSinalById(Long id) {
        return sinalRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Transactional(readOnly = true)
    public List<SinalDTO> getSinaisPorDispositivo(Long dispositivoId) {
        return sinalRepository.findByDispositivosIdOrderByDataHoraDesc(dispositivoId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public SinalDTO createSinal(SinalDTO dto) {
        // Validar e buscar Localização
        Localizacao localizacao = localizacaoRepository.findById(dto.getIdLocalizacao())
                .orElseThrow(() -> new ObjectNotFoundException("Localização não encontrada com ID: " + dto.getIdLocalizacao()));

        Sinal sinal = mapToEntity(dto);
        sinal.setLocalizacao(localizacao);

        // Associar dispositivos
        List<Dispositivo> dispositivos = new ArrayList<>();
        if (dto.getDispositivoIds() != null && !dto.getDispositivoIds().isEmpty()) {
            for (Long dispositivoId : dto.getDispositivoIds()) {
                Dispositivo dispositivo = dispositivoRepository.findById(dispositivoId)
                        .orElseThrow(() -> new ObjectNotFoundException("Dispositivo não encontrado com ID: " + dispositivoId));
                dispositivos.add(dispositivo);
            }
        }
        sinal.setDispositivos(dispositivos);

        Sinal savedSinal = sinalRepository.save(sinal);

        return mapToDTO(savedSinal);
    }

    @Transactional
    public SinalDTO updateSinal(Long id, SinalDTO dto) {
        if (sinalRepository.existsById(id)) {
            Sinal sinalAtualizado = mapToEntity(dto);
            sinalAtualizado.setId(id);

            // Atualizar Localização
            Localizacao localizacao = localizacaoRepository.findById(dto.getIdLocalizacao())
                    .orElseThrow(() -> new ObjectNotFoundException("Localização não encontrada com ID: " + dto.getIdLocalizacao()));
            sinalAtualizado.setLocalizacao(localizacao);

            // Atualizar dispositivos
            List<Dispositivo> dispositivos = new ArrayList<>();
            if (dto.getDispositivoIds() != null) {
                for (Long dispositivoId : dto.getDispositivoIds()) {
                    Dispositivo dispositivo = dispositivoRepository.findById(dispositivoId)
                            .orElseThrow(() -> new ObjectNotFoundException("Dispositivo não encontrado com ID: " + dispositivoId));
                    dispositivos.add(dispositivo);
                }
            }
            sinalAtualizado.setDispositivos(dispositivos);

            Sinal updatedSinal = sinalRepository.save(sinalAtualizado);
            return mapToDTO(updatedSinal);
        }
        return null;
    }

    @Transactional
    public void deleteSinal(Long id) {
        sinalRepository.findById(id).ifPresent(sinal -> {
            sinal.setDeleted(true);
            sinalRepository.save(sinal);
        });
    }

    public SinalDTO mapToDTO(Sinal sinal) {
        return SinalDTO.builder()
                .id(sinal.getId())
                .tipoSinal(sinal.getTipoSinal())
                .valor(sinal.getValor())
                .unidade(sinal.getUnidade() != null ? sinal.getUnidade().getUnidade() : null)
                .dataHora(sinal.getDataHora())
                .processamento(sinal.getProcessamento())
                .idLocalizacao(sinal.getLocalizacao() != null ? sinal.getLocalizacao().getId() : null)
                .dispositivoIds(sinal.getDispositivos() != null ?
                        sinal.getDispositivos().stream().map(Dispositivo::getId).collect(Collectors.toList()) : null)
                .dataCriacao(sinal.getDataCriacao())
                .build();
    }

    public Sinal mapToEntity(SinalDTO dto) {
        UnidadeSinalEnum unidadeEnum = UnidadeSinalEnum.convert(dto.getUnidade());
        if (unidadeEnum == null && dto.getUnidade() != null) {
            throw new IllegalArgumentException("Unidade inválida: " + dto.getUnidade());
        }

        return Sinal.builder()
                .tipoSinal(dto.getTipoSinal())
                .valor(dto.getValor())
                .unidade(unidadeEnum)
                .dataHora(dto.getDataHora())
                .processamento(dto.getProcessamento())
                .build();
    }
}