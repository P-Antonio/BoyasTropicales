package com.Boyas.Tropicales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Boyas.Tropicales.Entity.Ventas;
import com.Boyas.Tropicales.Service.VentasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/ventas")
public class VentasController {

	@Autowired
	private VentasService ventasService;
	
	@GetMapping
	public ResponseEntity<Page<T>> obtenerTodasLasVentas(@PageableDefault(size = 4, sort = {"id"}) Pageable paginacion){
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> obtenerUnaVenta(){
		
	}

	@PostMapping
	public ResponseEntity<?> crearVenta(@PathVariable @Valid CrearVenta crearVenta, UriComponentsBuilder componetBuilder){
	Ventas ventas = new Ventas(crearVenta);
	ventasService.save(ventas);
	
	var uri = componetBuilder.path("").buildAndExpand(null).toUri();
	return ResponseEntity.created(uri).body();
	}
	
	@PutMapping ("{id}")
	public ResponseEntity<?>actualizarVenta (){
	
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarVenta(){
	
	}
}
