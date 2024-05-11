package com.Boyas.Tropicales.controller.DTO.cosecha;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public record CrearCosechador(
		@NotBlank
		String nombre,
		@NotBlank
		String apellido,
		@NotBlank
		Integer cedula,
		@NotBlank
		Date fechaNacimiento,
		@NotBlank
		Date fechaInicioContrato) {

}
