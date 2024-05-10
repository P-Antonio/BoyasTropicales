package com.Boyas.Tropicales.controller.DTO.cosecha;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.Boyas.Tropicales.Entity.RegistroCosecha;
import com.Boyas.Tropicales.Entity.Enums.EnumArandanos;
import com.Boyas.Tropicales.Entity.Enums.EnumModulos;

public record RetornoDatosCosecha(float KilogramosCosechados, EnumModulos modulos, EnumArandanos tipoArandanos, LocalDate fechaCosecha) {

	public RetornoDatosCosecha(RegistroCosecha datos) {
		this(datos.getKilogramosCosechados(), datos.getModulos(), datos.getTipoArandano(), datos.getFechaCosecha());
	}
}
