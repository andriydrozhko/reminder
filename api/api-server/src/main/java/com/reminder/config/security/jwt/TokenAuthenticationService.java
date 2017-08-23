package com.reminder.config.security.jwt;


import com.reminder.entity.AuthenticatedUser;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static com.reminder.Constants.AUTHORIZATION_HEADER;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;

public class TokenAuthenticationService {

    private long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    private String secret = "8a27b136e18c165dc1f19ecc736c70cef8eaf40a";
    private static final String TOKEN_PREFIX = "Bearer";

    public void addAuthentication(HttpServletResponse response, String username) {
        // We generate a token now.
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(HS512, secret)
                .compact();
        response.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + " " + JWT);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER);
        if (token != null) {
            // parse the token.
            String username = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            if (username != null) {
                return new AuthenticatedUser(username);
            }
        }
        return null;
    }
}