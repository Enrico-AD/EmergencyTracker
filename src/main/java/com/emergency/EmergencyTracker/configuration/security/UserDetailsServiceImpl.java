package com.emergency.EmergencyTracker.configuration.security;

import com.emergency.EmergencyTracker.model.Login;
import com.emergency.EmergencyTracker.repository.LoginRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Login loginEntity = loginRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado: " + login));
        return loginEntity;
    }
}