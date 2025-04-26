package com.devmf.base.security.infrastructure.config.jwt;

import com.devmf.base.security.infrastructure.adapter.persistence.entity.RoleEntity;
import com.devmf.base.security.infrastructure.adapter.persistence.repository.RoleEntityRepository;
import com.devmf.base.security.infrastructure.config.constant.AuthTokenString;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final TokenGenerator tokenGenerator;

    private final RoleEntityRepository roleEntityRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (StringUtils.hasText(token) && tokenGenerator.verifyToken(token)) {
            String username = tokenGenerator.getUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (isDisabledUser(userDetails)) {
                respondWithForbidden(response, AuthTokenString.DISABLED_USER);
                return;
            }

            if (!isEnabledUser(userDetails)) {
                respondWithForbidden(response, AuthTokenString.DENIED_ACCESS);
                return;
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(AuthTokenString.AUTHORIZATION_HEADER);

        if (StringUtils.hasText(token) && token.startsWith(AuthTokenString.BEARER)) {
            return token.substring(AuthTokenString.BEARER_LENGTH);
        }

        return null;
    }

    private void respondWithForbidden(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(message);
    }

    private boolean isDisabledUser(UserDetails userDetails ) {
        Collection<? extends GrantedAuthority> userRoles = userDetails.getAuthorities();

        return userDetails.getAuthorities()
            .stream()
            .anyMatch(auth ->
                    AuthTokenString.ROLE_DISABLED_USER.equals(auth.getAuthority())
            );
    }

    private boolean isEnabledUser(UserDetails userDetails ) {
        Collection<? extends GrantedAuthority> userAuthorities = userDetails.getAuthorities();

        Set<String> enabledRoles = roleEntityRepository.findByEnabledTrue()
                .stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toSet());

        return userAuthorities
                .stream()
                .anyMatch(auth ->
                        enabledRoles.contains(auth.getAuthority())
                );
    }
}
