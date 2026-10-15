package com.sachin.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sachin.entity.AppUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	
	
	
	@Value("${jwt.secretKey}")
	private String sceretKey;
	
	
	private SecretKey getSigningKey()
	{
		return Keys.hmacShaKeyFor(sceretKey.getBytes(StandardCharsets.UTF_8));
		
	}
	
	public String generateToken(AppUser user)
	{
		long expirationtime = 1000L*60*15;
		
		return Jwts.builder()
				.subject(user.getUsername())
				.claim("userId" , user.getId().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+expirationtime))
				.signWith(getSigningKey())
				.compact();

	}
	
	public String extractUsername(String token)
	{
		return extractClaim(token,Claims::getSubject);
		
	}
	
	
	public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
	
	
	public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username)
                && extractExpiration(token).after(new Date());
    }
	
	
	private <T> T extractClaim( String token,  Function<Claims, T> claimsResolver) {

        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claimsResolver.apply(claims);
    }
	
	
	

}
