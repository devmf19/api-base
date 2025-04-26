package com.devmf.base.user.infrastructure.adapter.api.controller;

import com.devmf.base.common.model.Request;
import com.devmf.base.common.model.Response;
import com.devmf.base.user.application.dto.response.UserRes;
import com.devmf.base.user.application.port.in.UserManager;
import com.devmf.base.user.infrastructure.adapter.api.dto.request.UserReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor()
public class UserController {

    private final UserManager userManager;

    @GetMapping
    public Response<UserRes> ping() {
        return new Response<UserRes>().ok(true,"ping");
    }

    @PostMapping("/create")
    @Valid
    public Response<UserRes> create(@RequestBody Request<UserReq> request) {
        Response<UserRes> response = userManager.createUser(request.getData());
        return response;
    }

    @GetMapping("/{userId}")
    @Valid
    public Response<UserRes> get(@PathVariable Long userId) {
        Response<UserRes> response = userManager.getUserById(userId);
        return response;
    }
}
