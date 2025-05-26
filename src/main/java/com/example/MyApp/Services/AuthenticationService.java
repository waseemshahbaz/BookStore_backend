package com.example.MyApp.Services;

import com.example.MyApp.Entities.Role;
import com.example.MyApp.Entities.User;
import com.example.MyApp.Repositories.UserRepository;
import com.example.MyApp.config.JwtService;
import com.example.MyApp.dto.AuthenticationRequest;
import com.example.MyApp.dto.AuthenticationResponse;
import com.example.MyApp.dto.RegisterRequest;
import com.example.MyApp.dto.SignOutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public SignOutResponse signOut() {
        // Clear the security context
        SecurityContextHolder.clearContext();
        
        return SignOutResponse.builder()
                .message("Successfully signed out")
                .success(true)
                .build();
    }
} 