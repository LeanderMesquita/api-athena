package com.llm.athena.core.service;

import com.llm.athena.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    public String generateCommonToken(User user, Long expiresIn){
        String scope = user.getRole().toString();
        var claims = JwtClaimsSet.builder()
                .issuer("athena")
                .subject(user.getId())
                .issuedAt(Instant.now())
                .expiresAt(generateExpirationDate(expiresIn))
                .claim("scope", scope)
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private Instant generateExpirationDate(Long expiresIn) {
        return LocalDateTime.now().plusSeconds(expiresIn).toInstant(ZoneOffset.of("-03:00"));
    }
}
