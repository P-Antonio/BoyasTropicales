package com.Boyas.Tropicales.security;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTokenValidator extends OncePerRequestFilter{

	private JWTutils jwtUtils; 
	
	public JWTokenValidator(JWTutils jwtUtils) {
		super();
		this.jwtUtils = jwtUtils;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(jwtToken != null) {
			jwtToken = jwtToken.substring(7);
			DecodedJWT decodedJWT = jwtUtils.validarToken(jwtToken);
			
			String username = jwtUtils.extraerUsuario(decodedJWT);
			String authorizacion = jwtUtils.extraerClaimEspecifico(decodedJWT, "authorization").asString();
			
			Collection<? extends GrantedAuthority> listaAutorizaciones = AuthorityUtils.commaSeparatedStringToAuthorityList(authorizacion);
			
			SecurityContext contex = SecurityContextHolder.getContext();
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, listaAutorizaciones);
			contex.setAuthentication(authentication);
			SecurityContextHolder.setContext(contex);
		}
		filterChain.doFilter(request, response);	
	}

}
