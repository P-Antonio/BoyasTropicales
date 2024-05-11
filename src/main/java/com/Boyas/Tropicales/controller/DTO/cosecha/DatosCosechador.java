package com.Boyas.Tropicales.controller.DTO.cosecha;

import java.util.Date;

import com.Boyas.Tropicales.Entity.Cosechador;

public record DatosCosechador(String nombre,String apellido, Integer cedula, Date fechaNacimiento, Date fechaInicioContrato, Date fechaTerminoContrato) {

	public DatosCosechador(Cosechador cosechador) {
		this(cosechador.getNombre(), cosechador.getApellido(), cosechador.getCedula(), cosechador.getFechaNaciemiento(),
				cosechador.getFechaInicioContrato(), cosechador.getFechaTerminoContrato());
	}
}
