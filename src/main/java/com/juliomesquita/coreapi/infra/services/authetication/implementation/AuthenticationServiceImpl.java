package com.juliomesquita.coreapi.infra.services.authetication.implementation;

import com.juliomesquita.coreapi.domain.entities.Role;
import com.juliomesquita.coreapi.domain.entities.Token;
import com.juliomesquita.coreapi.domain.entities.User;
import com.juliomesquita.coreapi.infra.configurations.jwt.JwtRevokeTokens;
import com.juliomesquita.coreapi.infra.configurations.jwt.JwtRefreshTokenAuthentication;
import com.juliomesquita.coreapi.infra.configurations.jwt.JwtService;
import com.juliomesquita.coreapi.domain.interfaces.persistence.RoleRepository;
import com.juliomesquita.coreapi.domain.interfaces.persistence.TokenRepository;
import com.juliomesquita.coreapi.domain.interfaces.persistence.UserRepository;
import com.juliomesquita.coreapi.infra.requests.authentication.AuthenticationRequest;
import com.juliomesquita.coreapi.infra.requests.authentication.RegisterRequest;
import com.juliomesquita.coreapi.infra.responses.AuthenticationResponse;
import com.juliomesquita.coreapi.infra.services.authetication.factories.ExtraClaimsFactory;
import com.juliomesquita.coreapi.infra.services.authetication.factories.RoleFactory;
import com.juliomesquita.coreapi.infra.services.authetication.factories.TokenFactory;
import com.juliomesquita.coreapi.infra.services.authetication.factories.UserFactory;
import com.juliomesquita.coreapi.domain.interfaces.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;
    private final RoleFactory roleFactory;
    private final UserFactory userFactory;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JwtRevokeTokens jwtRevokeTokens;
    private final JwtRefreshTokenAuthentication jwtRefreshTokenAuthentication;

    @Transactional
    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        Role role = this.checkProfile();
        User user =
                this.userFactory.createUser(registerRequest.email(), registerRequest.password(), role);
        User userSaved = this.userRepository.save(user);
        String accessToken = this.createAccessToken(userSaved);
        String refreshToken = this.createRefreshToken(userSaved);
        Token token = TokenFactory.createToken(userSaved, accessToken);
        this.jwtRevokeTokens.revokeTokens(userSaved);
        this.tokenRepository.save(token);

        return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Transactional
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.email(),
                        authenticationRequest.password()
                )
        );
        User userSaved = this.userRepository.findByEmail(authenticationRequest.email())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        String accessToken = this.createAccessToken(userSaved);
        String refreshToken = this.createRefreshToken(userSaved);
        Token token = TokenFactory.createToken(userSaved, accessToken);
        this.jwtRevokeTokens.revokeTokens(userSaved);
        this.tokenRepository.save(token);
        return new AuthenticationResponse(accessToken, refreshToken);
    }

    @Transactional
    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.jwtRefreshTokenAuthentication.refreshToken(request, response);
    }

    private Role checkProfile() {
        Optional<Role> roleOptional = this.roleRepository.findByName("USER");
        if (roleOptional.isPresent()) {
            return roleOptional.get();
        }
        List<Role> roles = this.roleFactory.createRoles();
        List<Role> rolesSaved = this.roleRepository.saveAll(roles);
        return rolesSaved.stream()
                .filter(p -> p.getName().equals("ADMIN"))
                .findFirst()
                .orElseThrow();
    }

    private String createAccessToken(User user) {
        Map<String, Object> extraClaims = ExtraClaimsFactory.extraClaims(user);
        return this.jwtService.generateAccessToken(extraClaims, user);
    }

    private String createRefreshToken(User user) {
        Map<String, Object> extraClaims = ExtraClaimsFactory.extraClaims(user);
        return this.jwtService.generateRefreshToken(extraClaims, user);
    }
}
