package com.Boyas.Tropicales.controller.DTO.cosecha;

import com.Boyas.Tropicales.Entity.Ventas;

public record RetornoVentas(Long id, String empresa, int pedido, String entregado) {

	public RetornoVentas(Ventas ventas) {
		this(ventas.getId(), ventas.getEmpresa(), ventas.getPedido(), ventas.getEntregado());
	}
}
