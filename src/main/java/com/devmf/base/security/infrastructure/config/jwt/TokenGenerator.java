package com.devmf.base.security.infrastructure.config.jwt;


import com.devmf.base.security.infrastructure.config.constant.AuthTokenString;
import com.devmf.base.security.infrastructure.config.model.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Service
@Slf4j
public class TokenGenerator {

    @Value("${api.security.secret}")
    private String secret;

    @Value("${api.security.expirationInMs}")
    private Long expirationInMs; // 1 hour

    public String generateToken(Authentication authentication) {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        log.debug("{}", principalUser.getUsername());

        Set<String> authorities = principalUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return Jwts.builder()
                .setSubject(principalUser.getUsername())
                .claim(AuthTokenString.AUTHORITIES, authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expirationInMs))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public String getUsernameFromToken(String token) {
        log.debug("{}", "");

        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean verifyToken(String token) {
        log.debug("{}", "");

        try {
            Jwts.parser()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error(AuthTokenString.TOKEN_SIGNATURE_ERROR.concat(": "), ex.getMessage());
            throw new AuthenticationCredentialsNotFoundException(AuthTokenString.TOKEN_SIGNATURE_ERROR);
        } catch (MalformedJwtException ex) {
            log.error(AuthTokenString.TOKEN_INVALID_ERROR.concat(": "), ex.getMessage());
            throw new AuthenticationCredentialsNotFoundException(AuthTokenString.TOKEN_INVALID_ERROR);
        } catch (ExpiredJwtException ex) {
            log.error(AuthTokenString.TOKEN_EXPIRED_ERROR.concat(": "), ex.getMessage());
            throw new AuthenticationCredentialsNotFoundException(AuthTokenString.TOKEN_EXPIRED_ERROR);
        } catch (UnsupportedJwtException ex) {
            log.error(AuthTokenString.TOKEN_UNSUPPORTED_ERROR.concat(": "), ex.getMessage());
            throw new AuthenticationCredentialsNotFoundException(AuthTokenString.TOKEN_UNSUPPORTED_ERROR);
        } catch (IllegalArgumentException ex) {
            log.error(AuthTokenString.TOKEN_EMPTY_ERROR.concat(": "), ex.getMessage());
            throw new AuthenticationCredentialsNotFoundException(AuthTokenString.TOKEN_EMPTY_ERROR);
        }
    }
}
