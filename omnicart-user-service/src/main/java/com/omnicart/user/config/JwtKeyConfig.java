package com.omnicart.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Created by Ranjit Soni on 14-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Configuration
public class JwtKeyConfig {

    @Bean
    public PrivateKey jwtPrivateKey(@Value("${omnicart.security.jwt.private-key-location}") String keyLocation) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        /* Read private key file content */
        String pem = Files.readString(Path.of(keyLocation));

        String base64 = pem
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] encoded = Base64.getDecoder().decode(base64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }
}
