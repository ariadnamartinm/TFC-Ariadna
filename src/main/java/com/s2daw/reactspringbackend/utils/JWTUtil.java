package com.s2daw.reactspringbackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${security.jwt.secret}")
    private String key;

    @Value("${security.jwt.issuer}")
    private String issuer;

    @Value("${security.jwt.ttlMillis}")
    private long ttlMillis;

    private final Logger log = LoggerFactory.getLogger(JWTUtil.class);

    public String create(String id, String subject) {
        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            JwtBuilder builder = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(now)
                    .setSubject(subject)
                    .setIssuer(issuer)
                    .signWith(signatureAlgorithm, signingKey);

            if (ttlMillis >= 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);
            }

            return builder.compact();
        } catch (Exception e) {
            log.error("Error creating JWT token", e);
            throw new RuntimeException("Error creating JWT token", e);
        }
    }

    public String getValue(String jwt) {
        try {
            log.info("Parsing JWT: " + jwt);
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(key))
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims.getSubject();
        } catch (Exception e) {
            log.error("Error parsing JWT token", e);
            throw new RuntimeException("Error parsing JWT token", e);
        }
    }

    public String getKey(String jwt) {
        try {
            log.info("Parsing JWT: " + jwt);
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(key))
                    .parseClaimsJws(jwt)
                    .getBody();
            return claims.getId();
        } catch (Exception e) {
            log.error("Error parsing JWT token", e);
            throw new RuntimeException("Error parsing JWT token", e);
        }
    }
}
