package com.emergency.EmergencyTracker.service;

import com.emergency.EmergencyTracker.dto.LoginDTO;
import com.emergency.EmergencyTracker.exceptions.ObjectNotFoundException;
import com.emergency.EmergencyTracker.model.Login;
import com.emergency.EmergencyTracker.model.TipoLogin;
import com.emergency.EmergencyTracker.model.Usuario;
import com.emergency.EmergencyTracker.repository.LoginRepository;
import com.emergency.EmergencyTracker.repository.TipoLoginRepository;
import com.emergency.EmergencyTracker.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoLoginRepository tipoLoginRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public LoginDTO createLogin(@Valid LoginDTO loginDTO, Long usuarioId, Long tipoLoginId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com ID: " + usuarioId));
        TipoLogin tipoLogin = tipoLoginRepository.findById(tipoLoginId)
                .orElseThrow(() -> new ObjectNotFoundException("Tipo de login não encontrado com ID: " + tipoLoginId));

        Login login = Login.builder()
                .login(loginDTO.getLogin())
                .senha(passwordEncoder.encode(loginDTO.getSenha()))
                .usuario(usuario)
                .tipoLogin(tipoLogin)
                .build();

        Login saved = loginRepository.save(login);
        return mapToDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<LoginDTO> findAll() {
        return loginRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LoginDTO findById(Long id) {
        return loginRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ObjectNotFoundException("Login não encontrado com ID: " + id));
    }

    @Transactional
    public LoginDTO updateLogin(Long id, Long tipoLoginId, @Valid LoginDTO loginDTO) {
        return loginRepository.findById(id)
                .map(existing -> {
                    TipoLogin tipoLogin = tipoLoginRepository.findById(tipoLoginId)
                            .orElseThrow(() -> new ObjectNotFoundException("Tipo de login não encontrado com ID: " + tipoLoginId));
                    existing.setLogin(loginDTO.getLogin());
                    if (loginDTO.getSenha() != null && !loginDTO.getSenha().isBlank()) {
                        existing.setSenha(passwordEncoder.encode(loginDTO.getSenha()));
                    }
                    existing.setTipoLogin(tipoLogin);
                    Login updated = loginRepository.save(existing);
                    return mapToDTO(updated);
                })
                .orElseThrow(() -> new ObjectNotFoundException("Login não encontrado com ID: " + id));
    }

    @Transactional
    public void deleteLogin(Long id) {
        if (!loginRepository.existsById(id)) {
            throw new ObjectNotFoundException("Login não encontrado com ID: " + id);
        }
        loginRepository.deleteById(id);
    }

    private LoginDTO mapToDTO(Login login) {
        return LoginDTO.builder()
                .login(login.getLogin())
                .senha(null) // Não retorna a senha
                .build();
    }
}