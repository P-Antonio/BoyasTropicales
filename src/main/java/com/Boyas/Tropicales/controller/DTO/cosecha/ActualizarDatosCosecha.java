package com.Boyas.Tropicales.controller.DTO.cosecha;

import com.Boyas.Tropicales.Entity.Enums.EnumArandanos;
import com.Boyas.Tropicales.Entity.Enums.EnumModulos;

import jakarta.validation.constraints.NotBlank;

public record ActualizarDatosCosecha(
		@NotBlank
		Long id, 
		EnumModulos modulos, 
		EnumArandanos tipoArandano) {

}
