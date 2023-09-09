package com.projects.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtencoder;
    @Autowired
    private JwtDecoder jwtDecoder;

    public String generateJwt (Authentication auth) {
        //To get Current Time
        Instant now = Instant.now();
        //To map the Authorities (in our case , is admin and user)
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));//combine all of it to a single string

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles" , scope)
                .build();

        return  jwtencoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
