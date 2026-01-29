package com.eduplus.api.service;

import com.eduplus.api.dto.AuthRequest;
import com.eduplus.api.dto.AuthResponse;
import com.eduplus.api.dto.RegisterRequest;
import com.eduplus.api.model.User;
import com.eduplus.api.repository.UserRepository;
import com.eduplus.api.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        var jwtToken = jwtUtils.generateToken(user);
        
        return AuthResponse.builder()
            .token(jwtToken)
            .email(user.getEmail())
            .role(user.getRole().name())
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
    var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(com.eduplus.api.model.Role.USER) // Default role
            .build();
    
    userRepository.save(user);
    var jwtToken = jwtUtils.generateToken(user);
    
    return AuthResponse.builder()
            .token(jwtToken)
            .email(user.getEmail())
            .role(user.getRole().name())
            .build();
}
}