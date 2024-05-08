package com.Boyas.Tropicales.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Boyas.Tropicales.Entity.Usuario;
import com.Boyas.Tropicales.Reposiory.UsuarioRepository;

public class UserDetailsImp implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new BadCredentialsException("El usuario no existe"));
		
		
		List<SimpleGrantedAuthority> authority = new ArrayList<>();
		
		usuario.getRoles()
		.forEach(role -> authority.add(new SimpleGrantedAuthority("Role_".concat(role.getRoles().name()))));
		
		usuario.getRoles()
		.stream().flatMap(role -> role.getAuthorizations().stream())
		.forEach(authorizacion -> authority.add(new SimpleGrantedAuthority(authorizacion.getNombre())));
		
		return new User(usuario.getUsername(), 
				usuario.getPassword(),
				authority);
	}

}
