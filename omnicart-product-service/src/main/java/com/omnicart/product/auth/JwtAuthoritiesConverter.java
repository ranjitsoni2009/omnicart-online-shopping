package com.omnicart.product.auth;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ranjit Soni on 18-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Component
public class JwtAuthoritiesConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        List<String> claims = jwt.getClaimAsStringList("authorities");

        Collection<? extends GrantedAuthority> authorities =
                claims == null
                        ? List.of()
                        : claims.stream()
                        .distinct()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        return new JwtAuthenticationToken(jwt, authorities, Objects.requireNonNull(jwt.getSubject()));
    }
}
