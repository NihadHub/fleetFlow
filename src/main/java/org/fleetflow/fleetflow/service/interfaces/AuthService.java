package org.fleetflow.fleetflow.service.interfaces;

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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


public interface AuthService {

    AuthResponse register(RegistreRequest request);

    AuthResponse login(LoginRequest request);
}
