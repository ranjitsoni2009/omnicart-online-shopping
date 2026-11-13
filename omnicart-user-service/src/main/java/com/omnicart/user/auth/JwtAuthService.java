package com.omnicart.user.auth;

import com.omnicart.user.config.JwtProperties;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;

/**
 * Created by Ranjit Soni on 29-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthService {

    private final JwtProperties jwtProperties;

    public boolean isTokenValid(Claims claims, UserDetails userDetails) {

        if (!claims.getSubject().equals(userDetails.getUsername())) {
            log.error("Username mismatched");
            return false;
        }

        Date currentTime = new Date();
        if (currentTime.after(claims.getExpiration())) {
            log.error("Token Expired");
            return false;
        }

        if (currentTime.before(claims.getIssuedAt())) {
            log.error("Invalid Issued Time");
            return false;
        }

        if (!claims.getIssuer().equals(jwtProperties.getIssuer())) {
            log.error("Issuer mismatched");
            return false;
        }

        Set<String> audiences = claims.getAudience();

        boolean isAudienceMatched = Arrays.stream(jwtProperties.getAudiences()).anyMatch(audiences::contains);
        if (isAudienceMatched) {
            log.error("Audience mismatched");
            return false;
        }

        if (!userDetails.isEnabled() || !userDetails.isAccountNonLocked()) {
            log.error("Either User is disabled or account locked");
            return false;
        }
        return true;
    }
}
