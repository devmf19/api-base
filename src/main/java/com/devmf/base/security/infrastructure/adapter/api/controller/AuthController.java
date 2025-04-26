package com.devmf.base.security.infrastructure.adapter.api.controller;

import com.devmf.base.security.application.dto.response.AuthRes;
import com.devmf.base.security.application.port.in.AuthManager;
import com.devmf.base.security.infrastructure.adapter.api.dto.request.AuthReq;
import com.devmf.base.common.model.Request;
import com.devmf.base.common.model.Response;
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
public class AuthController {

    private final AuthManager authManager;

    @PostMapping("/")
    public Response<AuthRes> create(@RequestBody @Valid Request<AuthReq> request) {
        log.debug("{}", request);

        Response<AuthRes> response = authManager.authenticate(request.getData());

        return response;
    }
}
