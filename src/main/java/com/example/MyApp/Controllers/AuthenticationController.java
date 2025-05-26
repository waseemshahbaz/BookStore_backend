package com.example.MyApp.Controllers;

import com.example.MyApp.Services.AuthenticationService;
import com.example.MyApp.dto.AuthenticationRequest;
import com.example.MyApp.dto.AuthenticationResponse;
import com.example.MyApp.dto.RegisterRequest;
import com.example.MyApp.dto.SignOutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/signout")
    public ResponseEntity<SignOutResponse> signOut() {
        return ResponseEntity.ok(service.signOut());
    }
} 