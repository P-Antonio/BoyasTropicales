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
import com.Boyas.Tropicales.controller.DTO.cosecha.ActualizarVenta;
import com.Boyas.Tropicales.controller.DTO.cosecha.CrearVenta;
import com.Boyas.Tropicales.controller.DTO.cosecha.ObtenerVentas;
import com.Boyas.Tropicales.controller.DTO.cosecha.RetornoVentas;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/ventas")
public class VentasController {

	@Autowired
	private VentasService ventasService;
	
	@GetMapping
	public ResponseEntity<Page<ObtenerVentas>> obtenerTodasLasVentas(@PageableDefault(size = 4, sort = {"id"}) Pageable paginacion){
		var page = ventasService.findByEntregadoTrue(paginacion).map(ObtenerVentas :: new);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> obtenerUnaVenta(){
		
	}
	
	@PostMapping
	public ResponseEntity<?> crearVenta(@PathVariable @Valid CrearVenta crearVenta, UriComponentsBuilder componetBuilder){
	Ventas ventas = new Ventas(crearVenta);
	ventasService.save(ventas);
	
	var uri = componetBuilder.path("ventas/{id}").buildAndExpand(ventas.getId()).toUri();
	return ResponseEntity.created(uri).body(new RetornoVentas(ventas));
	}
	
	@PutMapping ("{id}")
	public ResponseEntity<?>actualizarVenta (@PathVariable ActualizarVenta actualizar){
		var ventaActualizada = ventasService.findById(actualizar.id());
		ventaActualizada.actualizar(actualizar);
		
		return ResponseEntity.ok(new RetornoVentas(ventaActualizada));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarVenta(@PathVariable Long id){
		ventasService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
