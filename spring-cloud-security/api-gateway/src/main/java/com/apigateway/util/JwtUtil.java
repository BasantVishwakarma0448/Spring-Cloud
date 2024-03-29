package com.apigateway.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.apigateway.exception.JwtTokenMalFormedException;
import com.apigateway.exception.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}

	public void validateToken(final String token) throws JwtTokenMalFormedException, JwtTokenMissingException {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalFormedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalFormedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalFormedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalFormedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty." + ex.getLocalizedMessage());
		}
	}

}
