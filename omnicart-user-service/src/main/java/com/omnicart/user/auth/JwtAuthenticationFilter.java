package com.omnicart.user.auth;

import com.omnicart.user.service.ICustomUserDetailsService;
import io.jsonwebtoken.*;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ranjit Soni on 29-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final PublicKey publicKey;
    private final JwtAuthService jwtAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            assert filterChain != null;
            filterChain.doFilter(request, response);
            return;
        }

        String token = tokenHeader.substring(7);
        try {
            JwtParser jwtParser = Jwts.parser().verifyWith(publicKey).build();
            Jws<Claims> jwsClaims = jwtParser.parseSignedClaims(token);
            Claims claims = jwsClaims.getPayload();
            String username = claims.getSubject();
            Collection<? extends GrantedAuthority> authorities = getGrantedAuthorities(claims);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtAuthService.isTokenValid(claims)) {
                   UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                           username, null, authorities);
                   authentication.setDetails(
                           new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext()
                           .setAuthentication(authentication);
               }
            }
        } catch (Exception exception) {
            log.error("JWT authentication failed: {}", exception.getMessage(), exception);
            SecurityContextHolder.clearContext();
        }
        assert filterChain != null;
        filterChain.doFilter(request, response);
    }

    private static Collection<? extends GrantedAuthority> getGrantedAuthorities(Claims claims) {
        Object object = claims.get("authorities");

        if (!(object instanceof Collection<?> authList)) {
            throw new IllegalArgumentException(
                    "Invalid or missing authorities claim");
        }

        return authList.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .distinct()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
