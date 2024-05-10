package com.Boyas.Tropicales.controller.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CrearCuenta(
		@NotBlank
		String username,
		@NotBlank
		String password,
		@Valid
		RequestRole role){

}
