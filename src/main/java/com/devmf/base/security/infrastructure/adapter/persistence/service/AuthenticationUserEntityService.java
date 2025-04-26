package com.devmf.base.security.infrastructure.adapter.persistence.service;

import com.devmf.base.security.application.dto.response.AuthRes;
import com.devmf.base.security.application.port.out.AuthRepository;
import com.devmf.base.security.infrastructure.adapter.api.dto.request.AuthReq;
import com.devmf.base.security.infrastructure.adapter.persistence.constant.AuthServiceString;
import com.devmf.base.security.infrastructure.config.jwt.TokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationUserEntityService implements AuthRepository {

    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;

    @Override
    public Optional<AuthRes> authenticateUser(AuthReq authReq) {
        log.debug("{} ", authReq);

        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authReq.getDocumentNumber(),
                    authReq.getPassword()
            );

            Authentication authenticationResult = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            String token = tokenGenerator.generateToken(authenticationResult);

            AuthRes authRes = AuthRes.builder()
                    .token(token)
                    .success(true)
                    .build();

            return Optional.of(authRes);

        } catch(BadCredentialsException e) {
            log.warn(AuthServiceString.AUTHENTICATION_FAILED_ERROR, authReq);
            return Optional.empty();
        } catch(AuthenticationException ex) {
            log.error(AuthServiceString.AUTHENTICATION_UNEXPECTED_ERROR, authReq, ex);
            throw ex;
        }

    }
}
