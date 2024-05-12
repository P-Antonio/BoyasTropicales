package com.Boyas.Tropicales.controller.DTO.cosecha;

import jakarta.validation.constraints.NotBlank;

public record CrearVenta(
		@NotBlank
		String empresa,
		@NotBlank
		int pedido) {

}
