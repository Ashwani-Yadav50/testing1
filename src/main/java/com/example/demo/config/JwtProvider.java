package com.example.demo.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    // Generate a secure key with sufficient size (e.g., 256 bits)
    private static SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    public static String generateToken(Authentication auth) {

        String jwt = Jwts.builder()
                .setIssuer("ashwani").setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt) {
        // Bearer token
        jwt = jwt.substring(7);

        Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        String email = String.valueOf(claims.get("email"));

        return email;
    }
}






//////////////////////////////////


//package com.example.demo.config;
//
//import java.util.Date;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.security.core.Authentication;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//public class JwtProvider {
//	
//	private static SecretKey key =Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//	
//	public static String generateToken(Authentication auth ) {
//		
//		String jwt = Jwts.builder()
//				.setIssuer("ashwani").setIssuedAt(new Date())
//				.setExpiration(new Date(new Date() .getTime() +86400000 ))
//				.claim("email", auth.getName())
//				.signWith(key)
//				.compact();
//		
//		return jwt;
//	}
//	
//	public static String getEmailFromJwtToken(String jwt) {
//		// Bearer token
//		jwt=jwt.substring(7);
//		
//		Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
//		
//		String email = String.valueOf(claims.get("email"));
//		
//		return email;
//		
//		}
//
//}
