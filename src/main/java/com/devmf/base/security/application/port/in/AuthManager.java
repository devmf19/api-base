package com.devmf.base.security.application.port.in;

import com.devmf.base.security.application.dto.response.AuthRes;
import com.devmf.base.security.infrastructure.adapter.api.dto.request.AuthReq;
import com.devmf.base.common.model.Response;

public interface AuthManager {

    /**
     * Authenticate a user with document number and password
     *
     * @param authReq User credentials
     * @return Response with authentication details
     */
    Response<AuthRes> authenticate(AuthReq authReq);
}
