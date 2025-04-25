package com.devmf.base.user.application.port.out;

import com.devmf.base.user.application.dto.response.UserRes;
import com.devmf.base.user.infrastructure.adapter.api.dto.request.UserReq;

import java.util.Optional;

public interface UserRepository {

    /**
     * Create a new user
     *
     * @param user User information
     * @return Response with creation details
     */
    Optional<UserRes> saveUser(UserReq user);

    /**
     * Validate if user exists by document number
     *
     * @param documentNumber User document number
     * @return Response con user information
     */
    Boolean existsUserByDocumentNumber(String documentNumber);

    /**
     * Get user by userId
     *
     * @param userId Unique user identification userId in db
     * @return Response con user information
     */
    Optional<UserRes> findUserById(Long userId);

}
