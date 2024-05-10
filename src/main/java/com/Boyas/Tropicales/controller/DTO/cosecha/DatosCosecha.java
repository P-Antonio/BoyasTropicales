package com.Boyas.Tropicales.controller.DTO.cosecha;

import com.Boyas.Tropicales.Entity.Enums.EnumArandanos;
import com.Boyas.Tropicales.Entity.Enums.EnumModulos;

public record DatosCosecha(float KilogramosCosechados, EnumModulos modulos, EnumArandanos tipoArandano) {

}
