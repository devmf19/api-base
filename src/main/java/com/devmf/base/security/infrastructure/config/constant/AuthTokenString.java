package com.devmf.base.security.infrastructure.config.constant;

public class AuthTokenString {
    public final static String AUTHORITIES = "authorities";
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String BEARER = "Bearer ";
    public final static int BEARER_LENGTH = 7;
    public final static String DENIED_ACCESS = "Acceso denegado";
    public final static String DISABLED_USER = "Usuario inhabilitado";
    public final static String ROLE_ADMIN_USER = "ADMIN";
    public final static String ROLE_DISABLED_USER = "DISABLED";
    public final static String ROLE_GENERAL_USER = "GENERAL_USER";
    public final static Long TEMPORAL_EXPIRATION = 3600000L;
    public final static String TEMPORAL_SECRET = "itsATemporarySecret";
    public final static String TOKEN_EMPTY_ERROR = "Token vacio";
    public final static String TOKEN_EXPIRED_ERROR = "Token expirado";
    public final static String TOKEN_INVALID_ERROR = "Token invalido";
    public final static String TOKEN_SIGNATURE_ERROR = "Error en la firma del token";
    public final static String TOKEN_UNSUPPORTED_ERROR = "Token no soportado";
}
