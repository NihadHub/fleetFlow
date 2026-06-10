package org.fleetflow.fleetflow.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fleetflow.fleetflow.dto.securityDTO.AuthResponse;
import org.fleetflow.fleetflow.dto.securityDTO.LoginRequest;
import org.fleetflow.fleetflow.dto.securityDTO.RegistreRequest;
import org.fleetflow.fleetflow.entity.Chauffeur;
import org.fleetflow.fleetflow.entity.User;
import org.fleetflow.fleetflow.enums.RoleUser;
import org.fleetflow.fleetflow.repository.ChauffeurRepository;
import org.fleetflow.fleetflow.repository.UserRepository;
import org.fleetflow.fleetflow.security.JwtUtil;
import org.fleetflow.fleetflow.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repo;
    private final ChauffeurRepository chauffeurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegistreRequest request) {
        User existing = repo.findByEmail(request.email());

        if (existing != null) {
            log.warn("Registration failed: Email {} already exists", request.email());
            throw new RuntimeException("Email déjà utilisé");
        }

        User user;
        if (request.role() == RoleUser.CHAUFFEUR) {
            Chauffeur chauffeur = new Chauffeur();
            chauffeur.setNom(request.username()); 
            chauffeur.setDisponible(true); 
            chauffeur.setTelephone(request.telephone());
            chauffeur.setPermisType(request.permisType());
            user = chauffeur;
        } else {
            user = new User();
        }

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        repo.save(user);

        String token = jwtUtil.genereteToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(request.email(), request.password());

            authenticationManager.authenticate(authToken);

            User user = repo.findByEmail(request.email());
            String token = jwtUtil.genereteToken(request.email(), user.getRole().name());
            return new AuthResponse(token);
        } catch (Exception e) {
            log.error("Login failed for {}: {}", request.email(), e.getMessage());
            throw e;
        }
    }
}
