package com.Boyas.Tropicales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Boyas.Tropicales.Service.RegistroService;

@RestController
@RequestMapping ("/registro")
public class RegistroCosechaController {

	@Autowired
	private RegistroService registroService;
	
	@GetMapping ("/obtenerRegistro")
	public ResponseEntity<?> obtenerRegistroCosecha(){
		return ResponseEntity.ok(registroService.findAll());
	}
}
