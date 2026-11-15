package com.omnicart.order.auth;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class JwtAuthoritiesConverter implements Converter<Jwt, AbstractAuthenticationToken> {

     public AbstractAuthenticationToken convert(Jwt jwt) {
         List<String> authorities = jwt.getClaimAsStringList("authorities");
         String subject = jwt.getSubject();
         assert subject != null;
         return new JwtAuthenticationToken(jwt, authorities == null ? List.of() : authorities.stream()
                 .distinct()
                 .map(SimpleGrantedAuthority::new)
                 .toList(), subject);
     }
}
