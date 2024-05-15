package com.juliomesquita.coreapi.infra.controllers;

import com.juliomesquita.coreapi.domain.interfaces.controllers.AuthenticationControllerDoc;
import com.juliomesquita.coreapi.domain.interfaces.services.AuthenticationService;
import com.juliomesquita.coreapi.infra.requests.AuthenticationRequest;
import com.juliomesquita.coreapi.infra.requests.RegisterRequest;
import com.juliomesquita.coreapi.infra.responses.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/authentication")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationControllerDoc {
    private final AuthenticationService authenticationService;
    @Override
    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        AuthenticationResponse response = this.authenticationService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        AuthenticationResponse response = this.authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.authenticationService.refreshToken(request, response);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok(null);
    }
}
