package com.Boyas.Tropicales.controller.DTO;

import jakarta.validation.constraints.NotBlank;

public record IniciarSession(
		@NotBlank
		String username,
		@NotBlank
		String password) {

}
