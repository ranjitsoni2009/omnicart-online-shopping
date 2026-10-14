package com.omnicart.user.service;

import com.omnicart.user.config.JwtProperties;
import com.omnicart.user.entity.CustomUserDetails;
import com.omnicart.user.entity.User;
import com.omnicart.user.exception.CustomBusinessException;
import com.omnicart.user.repo.IUserRepo;
import com.omnicart.user.util.OmniCartUserConstant;
import com.omnicart.user.util.OmniCartUtil;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ranjit Soni on 14-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class TokenService implements ITokenService {

    private PrivateKey privateKey;
    private final JwtProperties properties;
    private IUserRepo userRepo;

    public String generateToken(CustomUserDetails customUserDetails) {

        List<String> authorities = customUserDetails.getAuthorities().stream()
                .map(
                        GrantedAuthority::getAuthority)
                .distinct()
                .sorted()
                .toList();

        Date currentTime = new Date();
        Date expiryTime = Date.from(currentTime.toInstant().plusSeconds(360));

        return Jwts.builder()
                .header().keyId(properties.getActiveKeyId())
                    .and()
                .issuer(properties.getIssuer())
                .subject("USER_"+customUserDetails.getUserId())
                .audience().add(properties.getAudience())
                    .and()
                .issuedAt(currentTime)
                .expiration(expiryTime)
                .id(UUID.randomUUID().toString())
                .claim("authorities", authorities)
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
}
