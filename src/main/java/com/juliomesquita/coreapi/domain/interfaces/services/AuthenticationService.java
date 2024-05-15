package com.juliomesquita.coreapi.domain.interfaces.services;

import com.juliomesquita.coreapi.infra.requests.AuthenticationRequest;
import com.juliomesquita.coreapi.infra.requests.RegisterRequest;
import com.juliomesquita.coreapi.infra.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
