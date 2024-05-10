package com.Boyas.Tropicales.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Boyas.Tropicales.Entity.Role;
import com.Boyas.Tropicales.Entity.Usuario;
import com.Boyas.Tropicales.Reposiory.RoleRepository;
import com.Boyas.Tropicales.Reposiory.UsuarioRepository;
import com.Boyas.Tropicales.controller.DTO.AuthResponse;
import com.Boyas.Tropicales.controller.DTO.CrearCuenta;
import com.Boyas.Tropicales.controller.DTO.IniciarSession;
import com.auth0.jwt.JWT;

import jakarta.validation.Valid;

@Service
public class UserDetailsImp implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private JWTutils jwTutils;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;
	
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


	public AuthResponse login(@Valid IniciarSession iniciarSession) {
		String username = iniciarSession.username();
		String password = iniciarSession.password();
		
		Authentication authentication = this.autenticado(username, password);
		
		String token = jwTutils.crearToken(authentication);
		AuthResponse authResponse = new AuthResponse(username, "usuario logeado con exito", token, true);
		return authResponse;
	}


	private Authentication autenticado(String username, String password) {
		UserDetails userDetails = this.loadUserByUsername(username);
		if (username == null) {
			throw new BadCredentialsException("El usuario no existre");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("La contraseña es incorrecta");
		}
		
		Authentication authentication = new UsernamePasswordAuthenticationToken( userDetails.getUsername()
				,userDetails.getPassword(), userDetails.getAuthorities());
		
		return authentication;
	}


	public AuthResponse singUp(@Valid CrearCuenta crearCuenta) {
		String username = crearCuenta.username();
		String password = crearCuenta.password();
		List<String> roleRequest = crearCuenta.role().listaRoles();
		
		Set<Role> listaDeRoles = roleRepository.findRolesByRolesIn(roleRequest) .stream().collect(Collectors.toSet());
		
		if(listaDeRoles.isEmpty()) {
			throw new IllegalArgumentException("El rol escrito no existe");
		}
		
		 Usuario usuario = new Usuario().builder()
				 .username(username)
				 .password(passwordEncoder.encode(password))
				 .roles(listaDeRoles)
				 .build();
		 
		 Usuario usuarioCreado = usuarioRepository.save(usuario);
		 List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		 
		 usuarioCreado.getRoles()
		 .forEach(role -> authorityList.add(new SimpleGrantedAuthority("Rolw_".concat(role.getRoles().name()))));
		 
		 usuarioCreado.getRoles()
		 .stream().flatMap(role -> role.getAuthorizations().stream())
		 .forEach(autorizacion -> authorityList.add(new SimpleGrantedAuthority(autorizacion.getNombre())));
		 
		 Authentication authentication = new UsernamePasswordAuthenticationToken
				 (usuarioCreado.getUsername(), usuarioCreado.getPassword(), authorityList);
		 
		 String token = jwTutils.crearToken(authentication);
		 AuthResponse authResponse = new AuthResponse(username, "Usuario creado con exito", token, true);
		return authResponse;
	}

}
