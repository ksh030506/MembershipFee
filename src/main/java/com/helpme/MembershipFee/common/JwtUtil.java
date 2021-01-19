package com.helpme.MembershipFee.common;

import com.helpme.MembershipFee.domain.administratorMember.AdministratorMember;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
//Token Util 클래스
public class JwtUtil {

    final static public String ACCESS_TOKEN_NAME = "accessToken";
    private int tokenValidMilisecond = 1000 * 60 * 60 * 24;

    //secret 가져오기
    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    //Key를 가져오기
    private Key getSigningKey(String secretKey){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    } 

    public Claims extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //Token에서 UserEmail 가져오기
    public String getUserEmail(String token){
        return extractAllClaims(token).get("userEmail", String.class);
    }

    //HTTP Request Header에서 Token가져오기
    public String GetTokenByHeader(HttpServletRequest req){
        return req.getHeader("userEmail");
    }

    //Token생성 함수
    public String generateToken(AdministratorMember member) {
        return doGenerateToken(member.getEmail());
    }

    //UserEmail기반의 토큰 생성
    public String doGenerateToken(String userEmail){
        Claims claims = Jwts.claims();
        claims.put("userEmail", userEmail);
        Date now = new Date();
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    //토큰 검사
    public Boolean validateToken(String token, AdministratorMember member) {
        final String username = getUserEmail(token);
        try {
            if(username.equals(member.getEmail())){
                return true;
            }
        } catch (SignatureException ex){
            throw new SignatureException("Invalid JWT signature");
        } catch (MalformedJwtException ex){
            throw new MalformedJwtException("Invalid JWT token");
        } catch (UnsupportedJwtException ex){
            throw new UnsupportedJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("JWT claims string is empty.");
        } catch (NullPointerException ex){
            throw new NullPointerException("JWT Token is Null");
        }
        return false;
    }
}
