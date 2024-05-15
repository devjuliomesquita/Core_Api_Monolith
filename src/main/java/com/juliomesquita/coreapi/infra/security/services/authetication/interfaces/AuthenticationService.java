package com.juliomesquita.coreapi.infra.security.services.authetication.interfaces;

import com.juliomesquita.coreapi.infra.security.requests.AuthenticationRequest;
import com.juliomesquita.coreapi.infra.security.requests.RegisterRequest;
import com.juliomesquita.coreapi.infra.security.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
