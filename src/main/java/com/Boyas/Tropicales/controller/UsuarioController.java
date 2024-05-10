package com.Boyas.Tropicales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Boyas.Tropicales.controller.DTO.AuthResponse;
import com.Boyas.Tropicales.controller.DTO.CrearCuenta;
import com.Boyas.Tropicales.controller.DTO.IniciarSession;
import com.Boyas.Tropicales.security.UserDetailsImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/auth")
public class UsuarioController {

	@Autowired
	private UserDetailsImp userDetailsImp;
	
	
	@PostMapping("/log-in")
	public ResponseEntity<AuthResponse> login (@RequestBody @Valid IniciarSession iniciarSession){
		return new ResponseEntity<>(userDetailsImp.login(iniciarSession), HttpStatus.OK);
	}
	
	@PostMapping ("/sing-up")
	public ResponseEntity<AuthResponse> singUp (@RequestBody @Valid CrearCuenta crearCuenta){
		return new ResponseEntity<>(userDetailsImp.singUp(crearCuenta), HttpStatus.CREATED);
	}
}
