package com.omnicart.user.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Created by Ranjit Soni on 14-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@ConfigurationProperties(prefix = "omnicart.security.jwt")
@Validated
@Getter
@Setter
public class JwtProperties {


    private String issuer;
    private String audiences;
    private String accessTokenTtl;
    private String activeKeyId;

}
