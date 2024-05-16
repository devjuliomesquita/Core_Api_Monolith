package com.juliomesquita.coreapi.domain.interfaces.controllers;

import com.juliomesquita.coreapi.infra.requests.authentication.AuthenticationRequest;
import com.juliomesquita.coreapi.infra.requests.authentication.RegisterRequest;
import com.juliomesquita.coreapi.infra.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface AuthenticationControllerDoc {

    @PostMapping(value = "/register", consumes = "application/json")
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request);

    @PostMapping(value = "/login", consumes = "application/json")
    ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request);

    @PostMapping(value = "/refresh-token", consumes = "application/json")
    ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    @PostMapping(value = "/logout", consumes = "application/json")
    ResponseEntity<Void> logout();
}
