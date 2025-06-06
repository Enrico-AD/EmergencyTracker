/*package com.emergency.EmergencyTracker.configuration.security;

import com.emergency.EmergencyTracker.model.Usuario;
import com.emergency.EmergencyTracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.fibl(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com username: " + username));

        // Atualizar a data do último login
        usuario.setDataUltimoLogin(LocalDateTime.now());
        usuarioRepository.save(usuario);

        return usuario; // Retorna o usuário que implementa UserDetails
    }
}*/