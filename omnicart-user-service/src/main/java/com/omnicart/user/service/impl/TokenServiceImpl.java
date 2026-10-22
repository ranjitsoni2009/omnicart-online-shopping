package com.omnicart.user.service.impl;

import com.omnicart.user.config.JwtProperties;
import com.omnicart.user.entity.CustomUserDetails;
import com.omnicart.user.repo.IUserRepo;
import com.omnicart.user.service.ITokenService;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ranjit Soni on 14-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
@AllArgsConstructor
public class TokenServiceImpl implements ITokenService {

    private PrivateKey privateKey;
    private final JwtProperties properties;
    private IUserRepo userRepo;

    public String generateToken(CustomUserDetails customUserDetails, Date currentTime) {

        List<String> authorities = customUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .distinct()
                .sorted()
                .toList();

        Date expiryTime = Date.from(currentTime.toInstant().plusSeconds(360));

        return Jwts.builder()
                .header().keyId(properties.getActiveKeyId())
                    .and()
                .issuer(properties.getIssuer())
                .subject(String.valueOf(customUserDetails.getUserId()))
                .audience().add(properties.getAudiences())
                    .and()
                .issuedAt(currentTime)
                .expiration(expiryTime)
                .id(UUID.randomUUID().toString())
                .claim("authorities", authorities)
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
}
