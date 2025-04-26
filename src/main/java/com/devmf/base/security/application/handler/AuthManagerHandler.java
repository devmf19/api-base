package com.devmf.base.security.application.handler;

import com.devmf.base.security.application.dto.constant.AuthResponseString;
import com.devmf.base.security.application.dto.response.AuthRes;
import com.devmf.base.security.application.port.in.AuthManager;
import com.devmf.base.security.application.port.out.AuthRepository;
import com.devmf.base.security.infrastructure.adapter.api.dto.request.AuthReq;
import com.devmf.base.common.model.Response;
import com.devmf.base.user.application.dto.constant.UserResponseString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthManagerHandler implements AuthManager {

    private final AuthRepository authRepository;

    @Override
    public Response<AuthRes> authenticate(AuthReq authReq) {
        log.debug("{}", authReq);

        try {
            Optional<AuthRes> authRes = authRepository.authenticateUser(authReq);

            if(authRes.isEmpty()) {
                return new  Response<AuthRes>().unauthorized(AuthResponseString.BAD_CREDENTIALS);
            }

            return new Response<AuthRes>().ok(
                    UserResponseString.SUCCESS,
                    authRes.get()
            );
        } catch (AuthenticationException e) {
            return new  Response<AuthRes>().error(AuthResponseString.AUTHENTICATION_ERROR);
        }

    }

}
