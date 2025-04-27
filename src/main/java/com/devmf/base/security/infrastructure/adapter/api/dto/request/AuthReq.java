package com.devmf.base.security.infrastructure.adapter.api.dto.request;

import com.devmf.base.security.infrastructure.adapter.api.dto.constant.AuthRequestMessage;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@Data
public class AuthReq {

    @NotBlank(message = AuthRequestMessage.EMPTY_OR_NULL_FIELD)
    String documentNumber;

    @NotBlank(message = AuthRequestMessage.EMPTY_OR_NULL_FIELD)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    String password;
}
