package com.juliomesquita.coreapi.infra.security.services.authetication.implementation;

import com.juliomesquita.coreapi.domain.entities.Role;
import com.juliomesquita.coreapi.domain.entities.Token;
import com.juliomesquita.coreapi.domain.entities.User;
import com.juliomesquita.coreapi.infra.security.configurations.jwt.JwtService;
import com.juliomesquita.coreapi.infra.security.persistence.RoleRepository;
import com.juliomesquita.coreapi.infra.security.persistence.TokenRepository;
import com.juliomesquita.coreapi.infra.security.persistence.UserRepository;
import com.juliomesquita.coreapi.infra.security.requests.AuthenticationRequest;
import com.juliomesquita.coreapi.infra.security.requests.RegisterRequest;
import com.juliomesquita.coreapi.infra.security.responses.AuthenticationResponse;
import com.juliomesquita.coreapi.infra.security.services.authetication.factories.RoleFactory;
import com.juliomesquita.coreapi.infra.security.services.authetication.factories.TokenFactory;
import com.juliomesquita.coreapi.infra.security.services.authetication.factories.UserFactory;
import com.juliomesquita.coreapi.infra.security.services.authetication.interfaces.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
        this.revokeTokens(userSaved);
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
        this.revokeTokens(userSaved);
        this.tokenRepository.save(token);
        return new AuthenticationResponse(accessToken, refreshToken);
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
        Map<String, Object> extraClaims = this.extraClaims(user);
        return this.jwtService.generateAccessToken(extraClaims, user);
    }

    private String createRefreshToken(User user) {
        Map<String, Object> extraClaims = this.extraClaims(user);
        return this.jwtService.generateRefreshToken(extraClaims, user);
    }

    private Map<String, Object> extraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("Id", user.getId());
        extraClaims.put("Role", user.getRole().getName());
        extraClaims.put("Permissions", user.getRole().getAuthoraties());
        return extraClaims;
    }

    private void revokeTokens(User user) {
        List<Token> tokenList =
                this.tokenRepository.findByUserIdAndExpiredFalseAndRevokedFalse(user.getId());
        if (tokenList.isEmpty()) {
            return;
        }
        tokenList.forEach(t -> {
            t.setRevoked(true);
            t.setExpired(true);
        });
        this.tokenRepository.saveAll(tokenList);
    }
}
