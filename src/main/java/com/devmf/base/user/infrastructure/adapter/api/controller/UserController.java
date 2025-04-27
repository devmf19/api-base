package com.devmf.base.user.infrastructure.adapter.api.controller;

import com.devmf.base.common.model.Request;
import com.devmf.base.common.model.Response;
import com.devmf.base.user.application.dto.response.UserRes;
import com.devmf.base.user.application.port.in.UserManager;
import com.devmf.base.user.infrastructure.adapter.api.dto.request.UserReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Tag(name = "Gestion de usuarios", description = "Gestion y manejo de los usuarios del sistema")
public class UserController {

    private final UserManager userManager;

    @GetMapping
    @Operation(summary = "Ping", description = "Sirve para verificar que el servicio esta activo")
    public Response<UserRes> ping() {
        return new Response<UserRes>().ok(true,"ping");
    }

    @PostMapping("/create")
    @Operation(summary = "Crear usuario", description = "Permite la creacion de un nuevo usuario en el sistema")
    public Response<UserRes> create(@RequestBody Request<UserReq> request) {
        Response<UserRes> response = userManager.createUser(request.getData());
        return response;
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Buscar usuario por id", description = "Busca un usuario en el sistema por su id")
    public Response<UserRes> get(@PathVariable @NotNull Long userId) {
        Response<UserRes> response = userManager.getUserById(userId);
        return response;
    }
}
