package org.fleetflow.fleetflow.service;

import org.fleetflow.fleetflow.dto.securityDTO.AuthResponse;
import org.fleetflow.fleetflow.dto.securityDTO.LoginRequest;
import org.fleetflow.fleetflow.dto.securityDTO.RegistreRequest;


public interface AuthService {

    AuthResponse register(RegistreRequest request);

    AuthResponse login(LoginRequest request);
}
