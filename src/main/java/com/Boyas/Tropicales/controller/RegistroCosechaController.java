package com.Boyas.Tropicales.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Boyas.Tropicales.Entity.RegistroCosecha;
import com.Boyas.Tropicales.Service.RegistroService;
import com.Boyas.Tropicales.controller.DTO.cosecha.ActualizarDatosCosecha;
import com.Boyas.Tropicales.controller.DTO.cosecha.DatosCosecha;
import com.Boyas.Tropicales.controller.DTO.cosecha.RetornoDatosCosecha;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/registro")
public class RegistroCosechaController {

	@Autowired
	private RegistroService registroService;
	
	@GetMapping ("/obtenerRegistro")
	public ResponseEntity<?> obtenerRegistroCosecha(){
		return ResponseEntity.ok(registroService.findAll());
	}
	
	@GetMapping("/obtenerRegistroFecha{fecha}")
	public ResponseEntity<RetornoDatosCosecha> obtenerCosechaByFecha(@PathVariable Date fecha){
		var cosechaDia = registroService.findByFecha (fecha);
		return ResponseEntity.ok(new RetornoDatosCosecha(cosechaDia));
	}
	
	@PostMapping ("/crearRegistro")
	public ResponseEntity<?> crearRegistroCosecha(@RequestBody @Valid DatosCosecha datosCosecha, UriComponentsBuilder componentsBuilder){
		var cosecha = new RegistroCosecha(datosCosecha);
		registroService.save(cosecha);
		
		var uri = componentsBuilder.path("registroCosecha{id}").buildAndExpand(cosecha.getId()).toUri();
		return ResponseEntity.created(uri).body(new RetornoDatosCosecha(cosecha));
	}
	
	@PutMapping
	public ResponseEntity<?> actualizarRegistro(@RequestBody @Valid ActualizarDatosCosecha actualizar){
		var datosActualizados = registroService.findById(actualizar.id());
		datosActualizados.actualizarRegistro(actualizar);
		
		return ResponseEntity.ok(new RetornoDatosCosecha(datosActualizados));
	}
}
