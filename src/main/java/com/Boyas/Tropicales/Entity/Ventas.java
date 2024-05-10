package com.Boyas.Tropicales.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private int pedido;
	private boolean entregado = false; 
}
