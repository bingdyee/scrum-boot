package com.rechaox.scrum.util;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT helper
 *
 * @author yubinbin
 * @since 2021/05/22
 */
public final class Jwts {

    static final JWSHeader JWS_HEADER = new JWSHeader(JWSAlgorithm.HS256, JOSEObjectType.JWT, null, null, null, null, null, null, null, null, null, true,null, null);

    public static <T> T decode(String token, String claimKey, Class<T> clazz) {
        try {
            Object obj = SignedJWT.parse(token).getJWTClaimsSet().getClaim(claimKey);
            return JacksonUtils.toObject(JacksonUtils.toJson(obj), clazz);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T decodeAndVerify(String token, String secret, String claimKey, Class<T> clazz) {
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            if (!jwt.verify(new MACVerifier(secret.getBytes(StandardCharsets.UTF_8)))) {
                throw new RuntimeException("invalid token");
            }
            Date expireIn = jwt.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expireIn)) {
                throw new RuntimeException("expired token");
            }
            return decode(token, claimKey, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("invalid token");
        }
    }

    public static class Builder {

        private Map<String, Object> claims = new HashMap<>();

        private String secret;

        private String subject;

        private String issuer;

        private Integer expire;

        private String jti;

        private String aud;

        private Date issueTime;

        public Builder() {
            this.expire = 7200;
        }

        public Builder secret(String secret) {
            this.secret = secret;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder issuer(String issuer) {
            this.issuer = issuer;
            return this;
        }

        public Builder expire(Integer expire) {
            this.expire = expire;
            return this;
        }

        public Builder claim(String claimKey, final Object claim) {
            this.claims.put(claimKey, claim);
            return this;
        }

        public Builder jti(String jti) {
            this.jti = jti;
            return this;
        }

        public Builder aud(String audience) {
            this.aud = audience;
            return this;
        }

        public Builder issueTime(Date date) {
            this.issueTime = date;
            return this;
        }

        public String build() {
            try {
                if (StringUtils.isBlank(secret) || claims.isEmpty()) {
                    throw new RuntimeException("");
                }
                byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
                MACSigner macSigner = new MACSigner(secretBytes);
                JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder()
                        .expirationTime(new Date(System.currentTimeMillis() + expire * 1000));
                claims.forEach(builder::claim);
                if (StringUtils.isNotBlank(subject)) {
                    builder.subject(subject);
                }
                if (StringUtils.isNotBlank(issuer)) {
                    builder.issuer(issuer);
                }
                if (issueTime != null) {
                    builder.issueTime(issueTime);
                }
                if (StringUtils.isNotBlank(jti)) {
                    builder.jwtID(jti);
                }
                if (StringUtils.isNotBlank(aud)) {
                    builder.audience(aud);
                }
                JWTClaimsSet  claimsSet = builder.build();
                SignedJWT signedJwt = new SignedJWT(JWS_HEADER, claimsSet);
                signedJwt.sign(macSigner);
                return signedJwt.serialize();
            } catch (JOSEException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed create JWT token");
            }
        }

    }

}
