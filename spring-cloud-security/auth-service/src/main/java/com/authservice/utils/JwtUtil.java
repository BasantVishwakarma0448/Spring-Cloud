package com.authservice.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.authservice.exception.JwtTokenMalFormedException;
import com.authservice.exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long expiration;

	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + "-------" + e);
		}
		return null;
	}

	public String generateToken(String email) {
		Claims claims = Jwts.claims().setSubject(email);
		long nowMills = System.currentTimeMillis();
		long expMills = nowMills + expiration;
		Date exp = new Date(expMills);
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMills)).setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public void validateToken(final String token) throws JwtTokenMalFormedException, JwtTokenMissingException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException e) {
			throw new JwtTokenMalFormedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalFormedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalFormedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalFormedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}

	}
}
