package com.Boyas.Tropicales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Boyas.Tropicales.Entity.Cosechador;
import com.Boyas.Tropicales.Service.CosechadorService;
import com.Boyas.Tropicales.controller.DTO.cosecha.ActualizarCosechador;
import com.Boyas.Tropicales.controller.DTO.cosecha.CrearCosechador;
import com.Boyas.Tropicales.controller.DTO.cosecha.DatosCosechador;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/cosechador")
public class CosechadorController {

	@Autowired
	private CosechadorService cosechadorService;
	
	@GetMapping("/getCosechador")
	public ResponseEntity<?> obtenerCosechadores (){
		return ResponseEntity.ok(cosechadorService.findAll());
	}
	
	@GetMapping("getCosechador/{id}")
	public ResponseEntity<?> obtenerCosechadorById (@PathVariable Integer cedula){
		var ccCosechador = cosechadorService.findByCedula(cedula);
		
		return ResponseEntity.ok(new DatosCosechador(ccCosechador));
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> crearCosechador (@RequestBody @Valid CrearCosechador cosechador, UriComponentsBuilder componentsBuilder){
		var guardarCosechador = new Cosechador(cosechador); 
		cosechadorService.save(guardarCosechador);
		
		var uri = componentsBuilder.path("cosechador/{id}").buildAndExpand(guardarCosechador.getId()).toUri();
		return ResponseEntity.created(uri).body(new DatosCosechador(guardarCosechador));
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<?> actualizarCosechador (@RequestBody ActualizarCosechador actualizar){
		var cosechador = cosechadorService.findById(actualizar.id());
		cosechador.actualizarDatos(actualizar);
		return ResponseEntity.ok(new DatosCosechador(cosechador));
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarCosechador (@PathVariable Long id){
		var eliminar = cosechadorService.findById(id);
		eliminar.inhabilitar();
		
		return ResponseEntity.noContent().build();
	}
}
