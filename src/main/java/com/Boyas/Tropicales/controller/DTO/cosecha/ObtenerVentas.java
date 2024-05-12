package com.Boyas.Tropicales.controller.DTO.cosecha;

import com.Boyas.Tropicales.Entity.Ventas;

public record ObtenerVentas(Long id, String empresa, int pedido, String entregado) {

	public ObtenerVentas(Ventas ventas) {
		this(ventas.getId(), ventas.getEmpresa(), ventas.getPedido(), ventas.getEntregado());
	}
}
