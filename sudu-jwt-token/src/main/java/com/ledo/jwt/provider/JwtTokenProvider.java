package com.ledo.jwt.provider;

import com.ledo.jwt.config.JwtConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springside.modules.utils.text.JsonMapper;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

/**
 * Jwt token
 *
 * 1. create token
 * 2. valid token
 * Created by konghang on 2017/8/8.
 */
public class JwtTokenProvider {

    public static final String MODULE = "module";

    private JwtConfig jwtConfig;

    public JwtTokenProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createJwtToken(Payload payload) {
        //id
        String id = UUID.randomUUID().toString();

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfig.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //data
        String key = payload.key();
        String value = JsonMapper.INSTANCE.toJson(payload);
        DefaultClaims claims = new DefaultClaims();
        claims.put(MODULE, key);
        claims.put(key, value);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(jwtConfig.getSubject())
                .setIssuer(jwtConfig.getIssuer())
                .setClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (jwtConfig.getTtlMillis() >= 0) {
            long expMillis = nowMillis + jwtConfig.getTtlMillis();
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public Jws<Claims> parseJwtToken(String jwtToken) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtConfig.getSecret()))
                .parseClaimsJws(jwtToken);
        return claimsJws;
    }

    public <T extends Payload> T parseJwtToken(String jwtToken, Class<T> clazz) {
        Jws<Claims> claimsJws = parseJwtToken(jwtToken);
        return parseJwtToken(claimsJws, clazz);
    }

    public <T extends Payload> T parseJwtToken(Jws<Claims> claimsJws, Class<T> clazz) {
        Claims body = claimsJws.getBody();
        Payload payload = null;
        try {
            payload = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String value = body.get(payload.key(), String.class);
        return JsonMapper.INSTANCE.fromJson(value, clazz);
    }

}
