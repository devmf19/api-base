package com.devmf.base.security.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthRes {
    private String token;
    private boolean success;
}
