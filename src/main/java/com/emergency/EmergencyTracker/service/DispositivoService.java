package com.emergency.EmergencyTracker.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.emergency.EmergencyTracker.dto.DispositivoDTO;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.Dispositivo;
import com.emergency.EmergencyTracker.repository.DispositivoRepository;
import com.emergency.EmergencyTracker.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<DispositivoDTO> getAllDispositivos() {
        List<Dispositivo> dispositivos = dispositivoRepository.findAll();
        return dispositivos.stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<DispositivoDTO> getDispositivoById(Long id) {
        return dispositivoRepository.findById(id)
                .map(this::mapToDTO);
    }

    public DispositivoDTO createDispositivo(DispositivoDTO dto) {
        Dispositivo dispositivo = mapToEntity(dto);
        Dispositivo savedDispositivo = dispositivoRepository.save(dispositivo);
        return mapToDTO(savedDispositivo);
    }

    public DispositivoDTO updateDispositivo(Long id, DispositivoDTO dto) {
        if (dispositivoRepository.existsById(id)) {
            Dispositivo dispositivoAtualizado = mapToEntity(dto);
            dispositivoAtualizado.setId(id);
            Dispositivo updatedDispositivo = dispositivoRepository.save(dispositivoAtualizado);
            return mapToDTO(updatedDispositivo);
        }
        return null;
    }

    public void deleteDispositivo(Long id) {
        dispositivoRepository.findById(id).ifPresent(dispositivo -> {
            dispositivo.setDeleted(true);
            dispositivoRepository.save(dispositivo);
        });
    }

    public DispositivoDTO mapToDTO(Dispositivo dispositivo) {
        DispositivoDTO dto = new DispositivoDTO();
        dto.setId(dispositivo.getId());
        dto.setTipoDispositivo(dispositivo.getTipoDispositivo());
        dto.setStatus(dispositivo.getStatus());
        dto.setUsuarioId(dispositivo.getUsuario().getId());
        dto.setLatitude(dispositivo.getLatitude());
        dto.setLongitude(dispositivo.getLongitude());
        dto.setDataCriacao(dispositivo.getDataCriacao());
        return dto;
    }

    public Dispositivo mapToEntity(DispositivoDTO dto) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setTipoDispositivo(dto.getTipoDispositivo());
        dispositivo.setStatus(dto.getStatus());
        dispositivo.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado")));
        dispositivo.setLatitude(dto.getLatitude());
        dispositivo.setLongitude(dto.getLongitude());
        return dispositivo;
    }
}