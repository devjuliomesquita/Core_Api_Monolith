package com.juliomesquita.coreapi.access_control.domain.interfaces.services;

import com.juliomesquita.coreapi.access_control.infra.requests.authentication.AuthenticationRequest;
import com.juliomesquita.coreapi.access_control.infra.requests.authentication.RegisterRequest;
import com.juliomesquita.coreapi.access_control.infra.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
