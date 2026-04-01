package com.Productbasedcompany.employeeapi.util;

import java.security.Key;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	 private final String secret = "vsvdsvdgv-jbchabc-asjdhjavdjbdsbasjd4-ndhsvhgvd";


	 public String generateToken(String email) {

    	return Jwts.builder()
    			.setSubject(email)
    			.setIssuedAt(new Date())
    			.setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
    			.signWith(getSignKey(), SignatureAlgorithm.HS256)
    			.compact();
    }
    

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


	public String extractEmail(String token) {
		 
		return extractAllClaims(token).getSubject();
	}
	
	public Claims extractAllClaims(String token) {
		
		return Jwts.parserBuilder()
			    .setSigningKey(getSignKey())
			    .build()
			    .parseClaimsJws(token)
			    .getBody();
				
	}


	public boolean validatetoken(String token, UserDetails userdetails) {
		 final String email = extractEmail(token);
		 return (email).equals(userdetails.getUsername()) && !isTokenExpired(token);
	}


	private boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
	    return extractAllClaims(token).getExpiration();
	}


}