package com.devmf.base.security.application.port.out;

import com.devmf.base.security.application.dto.response.AuthRes;
import com.devmf.base.security.infrastructure.adapter.api.dto.request.AuthReq;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;

public interface AuthRepository {

    /**
     * Authenticate a user with document number and password
     *
     * @param authReq User credentials
     * @return Optional with token, can be empty if authentication fails
     */
    Optional<AuthRes> authenticateUser(AuthReq authReq) throws AuthenticationException;
}
