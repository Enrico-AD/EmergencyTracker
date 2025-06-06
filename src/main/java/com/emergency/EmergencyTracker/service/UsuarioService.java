package com.emergency.EmergencyTracker.service;

import com.emergency.EmergencyTracker.dto.UsuarioDTO;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.Usuario;
import com.emergency.EmergencyTracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Transactional
    public UsuarioDTO createUsuario(UsuarioDTO dto) {
        Usuario usuario = mapToEntity(dto);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return mapToDTO(savedUsuario);
    }

    @Transactional
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO dto) {
        return usuarioRepository.findById(id)
                .map(existing -> {
                    Usuario usuarioAtualizado = mapToEntity(dto);
                    usuarioAtualizado.setId(id);
                    Usuario updated = usuarioRepository.save(usuarioAtualizado);
                    return mapToDTO(updated);
                }).orElse(null);
    }

    @Transactional
    public void deleteUsuario(Long id) {
        usuarioRepository.findById(id)
                .ifPresent(usuario -> usuarioRepository.delete(usuario));
    }

    public UsuarioDTO mapToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .cpf(usuario.getCpf())
                .telefone(usuario.getTelefone())
                .email(usuario.getEmail())
                .build();
    }

    public Usuario mapToEntity(UsuarioDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .build();
    }
}