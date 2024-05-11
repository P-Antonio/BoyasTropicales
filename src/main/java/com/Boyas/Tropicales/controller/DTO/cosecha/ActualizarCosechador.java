package com.Boyas.Tropicales.controller.DTO.cosecha;

import java.util.Date;

public record ActualizarCosechador(Long id,String nombre,String apellido, Integer cedula, Date fechaNacimiento) {

}
