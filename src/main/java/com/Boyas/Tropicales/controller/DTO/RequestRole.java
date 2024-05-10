package com.Boyas.Tropicales.controller.DTO;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;
@Validated
public record RequestRole(
		@Size(max = 3, message = "El ususario no puede tener mas de 3 roles")
		List<String> listaRoles) {

}
