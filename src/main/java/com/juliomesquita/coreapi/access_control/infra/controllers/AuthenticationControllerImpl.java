package com.juliomesquita.coreapi.access_control.infra.controllers;

import com.juliomesquita.coreapi.access_control.domain.interfaces.controllers.AuthenticationControllerDoc;
import com.juliomesquita.coreapi.access_control.domain.interfaces.services.AuthenticationService;
import com.juliomesquita.coreapi.access_control.infra.requests.authentication.AuthenticationRequest;
import com.juliomesquita.coreapi.access_control.infra.requests.authentication.RegisterRequest;
import com.juliomesquita.coreapi.access_control.infra.responses.AuthenticationResponse;
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
