package com.Boyas.Tropicales.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class JWTutils {

	@Value ("${spring.security.my.privateKey}")
	private String claveSecreta;
	@Value ("${spring.security.my.generator}")
	private String gerenciador;
	
	public String crearToken (Authentication authentication) {
		Algorithm algoritmo = Algorithm.HMAC256(this.claveSecreta);
		
		String username = authentication.getPrincipal().toString();
		String authorizaciones = authentication.getAuthorities()
				.stream()
				.map(GrantedAuthority :: getAuthority)
				.collect(Collectors.joining(","));
		
		String token = JWT.create()
				.withIssuer(this.gerenciador)
				.withSubject(username)
				.withClaim("authorization", authorizaciones)
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + 7200000))
				.withJWTId(UUID.randomUUID().toString())
				.sign(algoritmo);
		return token;
	}
	
	
	public DecodedJWT validarToken (String token) {
		
		try {
			Algorithm algoritmo = Algorithm.HMAC256(this.claveSecreta);
			JWTVerifier verifiacion = JWT.require(algoritmo)
					.withIssuer(this.gerenciador)
					.build();
			
			DecodedJWT decodedJWT = verifiacion.verify(token);
			return decodedJWT;
		} catch (JWTVerificationException e) {
			throw new JWTVerificationException("El token es invalido");
		}
	}
	
	public String extraerUsuario (DecodedJWT decodedJWT) {
		return decodedJWT.getSubject().toString();
	}
	
	public Claim extraerClaimEspecifico (DecodedJWT decodedJWT, String Claim) {
		return decodedJWT.getClaim(Claim);
	}

	public Map<String, Claim> extraerClaims (DecodedJWT decodedJWT){
		return decodedJWT.getClaims();
	}
	
	private Instant fechaExpiracion() {
		return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-5:00"));
	}
}
