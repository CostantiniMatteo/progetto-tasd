package com.tasd.auth.config;

import com.tasd.auth.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.tasd.auth.model.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.tasd.auth.model.Constants.SIGNING_KEY;

@Component
public class JwtTokenUtil implements Serializable {

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims t = null;
        try {
            t = Jwts.parser()
                    .setSigningKey(SIGNING_KEY.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return doGenerateToken(user);
    }

    //TODO: scoprire come funzionano i ruoli di spring
    private String doGenerateToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("authorities", new ArrayList<String>() {{add(user.getRole().name());}});

        String t = null;
        try{
            t = Jwts.builder()
                    .setClaims(claims)
                    .setIssuer("http://devglan.com")
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                    .signWith(SignatureAlgorithm.HS256, SIGNING_KEY.getBytes("UTF-8"))
                    .compact();
        } catch (Exception e){
            e.printStackTrace();
        }
        return t;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}
