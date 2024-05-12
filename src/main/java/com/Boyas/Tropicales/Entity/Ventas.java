package com.Boyas.Tropicales.Entity;

import com.Boyas.Tropicales.controller.DTO.cosecha.ActualizarVenta;
import com.Boyas.Tropicales.controller.DTO.cosecha.CrearVenta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity (name ="Ventas")
@Table (name ="Ventas")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Ventas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String empresa;
	private Integer pedido;
	private String entregado = "El pedido aun no se ha entregado"; 
	
	public Ventas (@Valid CrearVenta venta) {
		this.empresa = venta.empresa();
		this.pedido = venta.pedido();
	}

	public void actualizar(ActualizarVenta actualizar) {
		if(actualizar.pedido() !=  null) {
			this.pedido = actualizar.pedido();
		}
		if(actualizar.entregado() != null) {
			this.entregado = actualizar.entregado();
		}
	}
}
