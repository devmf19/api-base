package com.devmf.base.security.infrastructure.adapter.api.controller;

import com.devmf.base.security.application.dto.response.AuthRes;
import com.devmf.base.security.application.port.in.AuthManager;
import com.devmf.base.security.infrastructure.adapter.api.dto.request.AuthReq;
import com.devmf.base.common.model.Request;
import com.devmf.base.common.model.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Autenticacion", description = "Autenticacion de usuarios en el sistema")
public class AuthController {

    private final AuthManager authManager;

    @PostMapping("/")
    @Operation(summary = "Autenticar usuario", description = "Autentica un usuario en el sistema con usuario y contraseña")
    public Response<AuthRes> create(@RequestBody @Valid Request<AuthReq> request) {
        log.debug("{}", request);

        Response<AuthRes> response = authManager.authenticate(request.getData());

        return response;
    }
}
