package org.fleetflow.fleetflow.controller;

import jakarta.validation.Valid;
import org.fleetflow.fleetflow.dto.securityDTO.AuthResponse;
import org.fleetflow.fleetflow.dto.securityDTO.LoginRequest;
import org.fleetflow.fleetflow.dto.securityDTO.RegistreRequest;
import org.fleetflow.fleetflow.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegistreRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
