package com.omnicart.user.auth;

import com.omnicart.user.service.ICustomUserDetailsService;
import io.jsonwebtoken.*;
import jakarta.annotation.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PublicKey;

/**
 * Created by Ranjit Soni on 29-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final PublicKey publicKey;
    private final ICustomUserDetailsService customUserDetailsService;
    private final JwtAuthService jwtAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nullable HttpServletResponse response, @Nullable FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer")) {
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
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
               UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
               if (jwtAuthService.isTokenValid(claims, userDetails)) {
                   UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                           userDetails, null, userDetails.getAuthorities());
                   authentication.setDetails(
                           new WebAuthenticationDetailsSource().buildDetails(request));
                   SecurityContextHolder.getContext()
                           .setAuthentication(authentication);
               }
            }
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
        }
        assert filterChain != null;
        filterChain.doFilter(request, response);
    }
}
