package com.portifolio.marketAPI.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService(SecurityConfig securityConfig) {
        this.passwordEncoder = securityConfig.passwordEncoder();
    }

    public String encode(String passwordRaw){
        return passwordEncoder.encode(passwordRaw);
    }

    public boolean matches(String passwordRaw, String passwordHash){
        return passwordEncoder.matches(passwordRaw, passwordHash);
    }
}
