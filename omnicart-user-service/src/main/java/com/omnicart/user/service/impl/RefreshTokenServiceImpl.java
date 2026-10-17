package com.omnicart.user.service.impl;

import com.omnicart.user.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;

/**
 * Created by Ranjit Soni on 16-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    private final SecureRandom secureRandom = new SecureRandom();

    @Value("${omnicart.security.jwt.refresh-token-bytes:32}")
    private int refreshTokenBytes;

    @Override
    public String generateRefreshToken() {

        byte[] randomBytes = new byte[refreshTokenBytes];

        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(randomBytes);
    }

    @Override
    public String hashRefreshToken(String refreshToken) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(refreshToken.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(
                    "SHA-256 algorithm is unavailable", ex);
        }

    }
}
